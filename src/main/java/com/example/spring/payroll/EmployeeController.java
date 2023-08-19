package com.example.spring.payroll;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    List<Employee> all(){
        return employeeRepository.findAll();
    }
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }
   @GetMapping("/employee/{id}")
    Employee one(@PathVariable Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()->new EmplyeeNotFoundException(id));
    }
   @PutMapping("/employee/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee,@PathVariable Long id){
        return employeeRepository.findById(id)
                .map(employee->{
                    employee.setName(newEmployee.getName());
                    employee.setRoles(newEmployee.getRoles());
                    return employeeRepository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }
    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
                
    }
}
