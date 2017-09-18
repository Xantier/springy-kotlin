dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.0")
}