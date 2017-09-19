package com.hallila.resty


import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Photo(
    val photo_album: PhotoAlbum,
    val highres_link: String,
    val caption: String = "",
    val photo_link: String,
    val member: Member
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Member(
    val name: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PhotoAlbum(
    val event: Event = Event("empty", 123)
)

@JsonIgnoreProperties
data class Event(
    val name: String,
    val id: Int
)