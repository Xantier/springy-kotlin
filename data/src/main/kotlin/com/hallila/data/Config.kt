package com.hallila.data


import org.springframework.context.support.BeanDefinitionDsl

fun BeanDefinitionDsl.dataBeans() {
    bean<DataRepository>()
    bean<Service> {
        ServiceImpl(ref())
    }
}