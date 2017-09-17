package com.hallila.resty

import org.springframework.context.support.BeanDefinitionDsl


fun BeanDefinitionDsl.restyBeans() {
    bean<RestyService> {
        RestyServiceImpl()
    }
}