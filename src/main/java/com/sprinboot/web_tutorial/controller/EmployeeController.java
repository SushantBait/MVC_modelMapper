package com.sprinboot.web_tutorial.controller;

import com.sprinboot.web_tutorial.dto.EmployeeDTO;
import com.sprinboot.web_tutorial.entity.EmployeeEntity;
import com.sprinboot.web_tutorial.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController{

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping(path ="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : asdfglkj";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        //@PathVariable make sure {employeeId} injected to the String employeeId
        // whenever we hit the endpoint we get json data -- with the help for Jackson- it converted java object to Json
        //When we are mark @PathVariable it's a required data

//        return new EmployeeDTO(employeeId, "Sushant", "sushant@gmail.com", 31, LocalDate.of(2023, 01, 15), true);
        return employeeService.getEmployeeById(employeeId);
    }


    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){


        //When we are mark @RequestParam it's an optional data but need to do with @RequestParam(required = false)
       return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeEntity inputEmployee){

        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeDTO, employeeId);
    }

    @DeleteMapping(path = "{/{employeeId}}")
    public void deleteEmployeeById(Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }

}
