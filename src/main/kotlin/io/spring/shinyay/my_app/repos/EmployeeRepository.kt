package io.spring.shinyay.my_app.repos

import io.spring.shinyay.my_app.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeRepository : JpaRepository<Employee, Long>
