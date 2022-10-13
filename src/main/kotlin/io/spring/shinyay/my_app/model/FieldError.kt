package io.spring.shinyay.my_app.model


data class FieldError(
    var `field`: String? = null,
    var errorCode: String? = null
)
