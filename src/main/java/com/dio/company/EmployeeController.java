package com.dio.company;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){

        this.repository = repository;
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee){

        return repository.save(newEmployee);
    }

    @GetMapping("/employees")
    public List<Employee> listOfEmployeeAll(){
        return repository.findAll();
    }

    // Utilizando o orElseThrow() para lançar exceção, atenção na necessidade da ()-> para lançar como função
    @GetMapping("/employees/{id}")
    public Employee consultById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    //Sem o lançamento de exceção e somente utilizando o <Optional>
    //    @GetMapping("/employess/{id}")
    //    public Optional<Employee> consultById(@PathVariable Long id){
    //        return repository.findById(id);
    //    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, long id) {
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setAddress(newEmployee.getAddress());
            employee.setRole(newEmployee.getRole());
            return repository.save(newEmployee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("employees/{id}")
    void deleteEmployee(@PathVariable long id){
        repository.deleteById(id);
    }



}
