package com.hallila.util

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.toMono


fun ServerResponse.BodyBuilder.html(content: String) =
    contentType(MediaType.TEXT_HTML).body(content.toMono())

inline fun <reified T : Any> ServerResponse.BodyBuilder.json(content: T) =
    contentType(MediaType.APPLICATION_JSON).body(content.toMono())