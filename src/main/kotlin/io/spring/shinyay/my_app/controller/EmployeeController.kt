package io.spring.shinyay.my_app.controller

import io.spring.shinyay.my_app.model.EmployeeDTO
import io.spring.shinyay.my_app.service.EmployeeService
import io.spring.shinyay.my_app.util.WebUtils
import javax.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("employees", employeeService.findAll())
        return "employee/list"
    }

    @GetMapping("/add")
    fun add(@ModelAttribute("employee") employeeDTO: EmployeeDTO): String = "employee/add"

    @PostMapping("/add")
    fun add(
        @ModelAttribute("employee") @Valid employeeDTO: EmployeeDTO,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        if (bindingResult.hasErrors()) {
            return "employee/add"
        }
        employeeService.create(employeeDTO)
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS,
                WebUtils.getMessage("employee.create.success"))
        return "redirect:/employees"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        model.addAttribute("employee", employeeService.get(id))
        return "employee/edit"
    }

    @PostMapping("/edit/{id}")
    fun edit(
        @PathVariable id: Long,
        @ModelAttribute("employee") @Valid employeeDTO: EmployeeDTO,
        bindingResult: BindingResult,
        redirectAttributes: RedirectAttributes
    ): String {
        if (bindingResult.hasErrors()) {
            return "employee/edit"
        }
        employeeService.update(id, employeeDTO)
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS,
                WebUtils.getMessage("employee.update.success"))
        return "redirect:/employees"
    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long, redirectAttributes: RedirectAttributes): String {
        employeeService.delete(id)
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO,
                WebUtils.getMessage("employee.delete.success"))
        return "redirect:/employees"
    }

}
