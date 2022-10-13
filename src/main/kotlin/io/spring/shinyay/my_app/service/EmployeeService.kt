package io.spring.shinyay.my_app.service

import io.spring.shinyay.my_app.domain.Employee
import io.spring.shinyay.my_app.model.EmployeeDTO
import io.spring.shinyay.my_app.repos.EmployeeRepository
import kotlin.streams.toList
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    fun findAll(): List<EmployeeDTO> {
        return employeeRepository.findAll(Sort.by("id"))
                .stream()
                .map { employee -> mapToDTO(employee, EmployeeDTO()) }
                .toList()
    }

    fun `get`(id: Long): EmployeeDTO {
        return employeeRepository.findById(id)
                .map { employee -> mapToDTO(employee, EmployeeDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(employeeDTO: EmployeeDTO): Long {
        val employee = Employee()
        mapToEntity(employeeDTO, employee)
        return employeeRepository.save(employee).id!!
    }

    fun update(id: Long, employeeDTO: EmployeeDTO) {
        val employee: Employee = employeeRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) } 
        mapToEntity(employeeDTO, employee)
        employeeRepository.save(employee)
    }

    fun delete(id: Long) {
        employeeRepository.deleteById(id)
    }

    fun mapToDTO(employee: Employee, employeeDTO: EmployeeDTO): EmployeeDTO {
        employeeDTO.id = employee.id
        employeeDTO.firstname = employee.firstname
        employeeDTO.lastname = employee.lastname
        employeeDTO.role = employee.role
        employeeDTO.age = employee.age
        return employeeDTO
    }

    fun mapToEntity(employeeDTO: EmployeeDTO, employee: Employee): Employee {
        employee.firstname = employeeDTO.firstname
        employee.lastname = employeeDTO.lastname
        employee.role = employeeDTO.role
        employee.age = employeeDTO.age
        return employee
    }

}
