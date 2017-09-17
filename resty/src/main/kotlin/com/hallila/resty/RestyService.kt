package com.hallila.resty

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux


interface RestyService {
    fun getAttendees(): Flux<String>
}

internal class RestyServiceImpl : RestyService {

    @Value("\${meetup_api_key}")
    private lateinit var key: String

    override fun getAttendees(): Flux<String> {
        val client = WebClient.create("https://api.meetup.com")
        return client.get()
            .uri("/Dublin-Kotliners/events/243009138/rsvps")
            .attribute("key", key)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMapMany { response ->
                response.bodyToFlux<String>()
            }
    }
}
