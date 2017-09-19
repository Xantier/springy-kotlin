package com.hallila.data


import javax.persistence.*

@Entity
@Table(name = "language")
data class Language(
    val name: String,
    val creator: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val description: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    val targets: List<String> = listOf()
)