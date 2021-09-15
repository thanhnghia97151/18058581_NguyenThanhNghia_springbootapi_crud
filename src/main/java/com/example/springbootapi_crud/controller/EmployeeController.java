package com.example.springbootapi_crud.controller;

import com.example.springbootapi_crud.entity.Employee;
import com.example.springbootapi_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Employee> employee = employeeService.getAllEmployee();

        model.addAttribute("employees", employee);

        return "index";
    }

    @RequestMapping(value = "add")
    public String addUser(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long employeeId, Model model) {
        Optional<Employee> employeeEdit = employeeService.findEmployeeById(employeeId);
        employeeEdit.ifPresent(employee -> model.addAttribute("employee", employee));
        return "editEmployee";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long employId, Model model) {
        employeeService.deleteEmployee(employId);
        return "redirect:/";
    }
}
