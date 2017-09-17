package com.hallila

import com.hallila.data.dataBeans
import com.hallila.resty.restyBeans
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@SpringBootApplication
class SpringyKotlinApplication

fun main(args: Array<String>) {
    val springApplication = SpringApplication(SpringyKotlinApplication::class.java)
    springApplication.addInitializers(ApplicationContextInitializer<GenericApplicationContext> { ctx ->
        beans {
            bean {
                Handler(ref(), ref())
            }
            bean { Routes.indexRouter(ref()) }
            dataBeans()
            restyBeans()
        }.initialize(ctx)
    })
    springApplication.run(*args)
}
