package com.example.Employee.Controller;

import com.example.Employee.Model.Employee;
import com.example.Employee.Service.serviceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //   to tell that we are creating REST API that does not use the UI
@RequestMapping ("/emp")  // to map the 'model class' or whole project components with URL

public class controllerClass {
@Autowired                              //to link 'controller class' with 'service class'
                                         // by creation of the object of the 'service class'
private serviceClass employeeService ;   // creation of object of the 'service class'

@PostMapping ("/create")
 public ResponseEntity<Employee>  create(@RequestBody Employee employee){
   // Request Body used to send the complex or larger data like complete credentials of employee as an object
    // and it contains JSON format sent in the body of Http Request
    if(employeeService.saveEmployee(employee)) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        // in this we re saying that if we get request to create an employee then create it and provide
        // a valid Http Status code
    }
        else{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(employee);}
       // if employee already exists then show a conflict with a valid Http status Code
    }

@GetMapping ("/get/{name}")

public ResponseEntity<Employee> getEmployee(@PathVariable("name")String name){
 //when we need to get some specific resource like id or name. "PathVariable" passes value to API
  Employee employee  = employeeService.findByName(name);
  // creation of findByName method and object of Employee class takes value from the object of Service class
  if(employee !=null){
      return ResponseEntity.status(HttpStatus.OK).body(employee);
      // returns the status code 'OK'
  }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> findAllEmployees() {
    List<Employee> employees = employeeService.findAllEmployees();
    if (!employees.isEmpty()) {
        return ResponseEntity.status(HttpStatus.OK).body(employees);    }
    else {        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();    }}



 @PutMapping("/modify")
 public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
Employee updatedEmployee=employeeService.updateEmployee(employee)  ;
if(updatedEmployee!=null){
return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
}else
{return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

}}
    @DeleteMapping("/delete/{id}")
//@DeleteMapping("/del/{id)")
 public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id){
   boolean isDeleted =employeeService.deleteEmployee(id);
   if(isDeleted) {
       return ResponseEntity.status(HttpStatus.OK).build();
   }  else{
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


   }
}
}



