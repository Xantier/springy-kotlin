package com.hallila.data


import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.function.BiFunction


interface Service {
    fun getLanguages(): List<Language>
    fun getLanguagesStream(): Flux<Language>
}

internal class ServiceImpl(private val dataRepository: DataRepository) : Service {
    override fun getLanguages() = dataRepository.findAll().toList()

    override fun getLanguagesStream(): Flux<Language> =
        dataRepository.findAll()
            .toFlux()
            .zipWith(
                Flux.interval(
                    Duration.of(
                        (0..3000).random().toLong(),
                        ChronoUnit.MILLIS)
                ), BiFunction { language, _ ->
                language
            })
}

private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start