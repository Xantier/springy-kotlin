plugins {
    val kotlinVersion = "1.1.4-3"
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
}
dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    val postgresVersion: String = parent.properties["postgresVersion"] as String
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
}