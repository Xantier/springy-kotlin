package com.hallila.resty

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux


interface RestyService {
    fun getPhotos(): Flux<Photo>
}

internal class RestyServiceImpl : RestyService {

    @Value("\${meetup_api_key}")
    private lateinit var key: String

    val eventKey: Int = 243009138

    override fun getPhotos(): Flux<Photo> {
        val client = WebClient.create("https://stream.meetup.com")
        return client.get()
            .uri("/2/photos")
            .attribute("key", key)
            .accept(MediaType.APPLICATION_OCTET_STREAM)
            .exchange()
            .flatMapMany { it ->
                it.bodyToFlux<Photo>()
            }
            .log()
            .filter{
                it.photo_album.event.id == eventKey
            }

    }
}
