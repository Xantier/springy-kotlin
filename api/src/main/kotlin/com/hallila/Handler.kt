package com.hallila

import com.hallila.data.Service
import com.hallila.resty.RestyService
import com.hallila.util.html
import com.hallila.util.json
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body


class Handler(private val service: Service,
              private val restyService: RestyService) {
    fun renderView(req: ServerRequest) =
        ok().html(
            createHTML(true).html {
                head {
                    title = "Dublin Kotliners"
                    styleLink("/static/something.css")
                }
                body {
                    h4 { +"Hello HubSpot" }
                    p { +"" }
                    canvas {
                        id = "canvas"
                        style = "position:absolute; top:0; left:0;"
                    }
                    div {
                        id = "buffer"
                        style = "display: none;"
                    }
                    script(src = "/static/nonsense.js")
                }
            }
        )


    fun sayHello(req: ServerRequest) =
        ok().json("Hello Kotliners")

    fun sayHallo(req: ServerRequest) =
        ok().json("Hello ${req.queryParam("name").orElse("Dubliners")}")

    fun streamingResponse(req: ServerRequest) =
        ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(service.getLanguagesStream())

    fun renderStreamView(req: ServerRequest) =
        ok().html(
            createHTML(true).html {
                head {
                    title = "Language Race"
                }
                body {
                    service.getLanguages().map {
                        div(classes = "container") {
                            id = it.name
                        }
                    }
                    script(src = "/static/oboe.min.js")
                    script(src = "/static/streaming.js")
                }
            }
        )


    fun restyResponse(req: ServerRequest) =
        ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(restyService.getPhotos())

    fun renderRestyView(req: ServerRequest) =
        ok().html(
            createHTML(true).html {
                head {
                    title = "It's finally over"
                    styleLink(url = "/static/photos.css")
                }
                body {
                    section {
                        id = "photos"
                    }
                    script(src = "/static/oboe.min.js")
                    script(src = "/static/photos.js")
                }
            }
        )

}