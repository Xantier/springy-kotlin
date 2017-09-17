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
                        attributes["id"] = "canvas"
                        attributes["style"] = "position:absolute; top:0; left:0;"
                    }
                    div {
                        attributes["id"] = "buffer"
                        attributes["style"] = "display: none;"
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
                            attributes["id"] = it.name
                        }
                    }
                    script(src = "/static/oboe.min.js")
                    script(src = "/static/streaming.js")
                }
            }
        )


    fun restyResponse(req: ServerRequest) =
        ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(restyService.getAttendees())

    fun renderRestyView(req: ServerRequest) =
        ok().html(
            createHTML(true).html {
                head {
                    title = "It's finally over"
                }
                body {
                    div(classes = "container") {
                        attributes["style"] = "width:100%; margin:0 auto;position:absolute; top:0; left:0;"
                        img(src = "https://secure.meetupstatic.com/photos/member/d/8/3/8/member_163015352.jpeg") {
                            attributes["style"] = "margin: 10px 0 0 45%;"
                        }
                    }
                    div {
                        attributes["id"] = "buffer"
                        attributes["style"] = "display: none;"
                    }
                    canvas {
                        attributes["id"] = "canvas"
                        attributes["style"] = "float:left;"
                    }
                    script(src = "/static/oboe.min.js")
                    script(src = "/static/nonsense.js")
                }
            }
        )
}