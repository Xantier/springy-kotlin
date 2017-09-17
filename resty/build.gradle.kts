dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("com.beust:klaxon:0.30")
}