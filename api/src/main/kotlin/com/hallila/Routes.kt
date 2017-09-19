package com.hallila

import com.hallila.util.WithLogging
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

object Routes : WithLogging() {

    fun indexRouter(handler: Handler) =
        router {
            "/view".nest {
                GET("/", handler::renderView)
                GET("/stream", handler::renderStreamView)
                GET("/resty", handler::renderRestyView)
            }
            ("/stream" and accept(MediaType.APPLICATION_STREAM_JSON)).nest {
                GET("/data", handler::streamingResponse)
                GET("/resty", handler::restyResponse)
            }
            "/".nest {
                GET("/", handler::sayHello)
                GET("/name", handler::sayHallo)
            }
            resources("/**", ClassPathResource("/static"))
        }.filter { request, next ->
            log.debug(request)
            next.handle(request)
        }
}