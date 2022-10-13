package io.spring.shinyay.my_app.rest

import io.spring.shinyay.my_app.model.EmployeeDTO
import io.spring.shinyay.my_app.service.EmployeeService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.lang.Void
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/employees"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class EmployeeResource(
    private val employeeService: EmployeeService
) {

    @GetMapping
    fun getAllEmployees(): ResponseEntity<List<EmployeeDTO>> =
            ResponseEntity.ok(employeeService.findAll())

    @GetMapping("/{id}")
    fun getEmployee(@PathVariable id: Long): ResponseEntity<EmployeeDTO> =
            ResponseEntity.ok(employeeService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createEmployee(@RequestBody @Valid employeeDTO: EmployeeDTO): ResponseEntity<Long> =
            ResponseEntity(employeeService.create(employeeDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateEmployee(@PathVariable id: Long, @RequestBody @Valid employeeDTO: EmployeeDTO):
            ResponseEntity<Void> {
        employeeService.update(id, employeeDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<Void> {
        employeeService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
