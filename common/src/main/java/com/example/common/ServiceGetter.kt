package com.example.common

import java.util.ServiceLoader

object ServiceGetter {

    fun <S : Any> getService(service:Class<S> ): S? {
        val serviceLoader = ServiceLoader.load(service)
        val iterator = serviceLoader.iterator()
        while (iterator.hasNext()) {
            return iterator.next()
        }
        return  null
    }
}