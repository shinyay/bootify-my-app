package io.spring.shinyay.my_app.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class EmployeeDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var firstname: String? = null

    @NotNull
    @Size(max = 255)
    var lastname: String? = null

    @NotNull
    @Size(max = 255)
    var role: String? = null

    @NotNull
    var age: Int? = null

}
