package com.Ibrahim.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @Valid @RequestBody StudentDto dto
    ) {
        return this.studentService.saveStudent(dto);
    }


    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(
            @PathVariable("student-id") Integer id
    ) {
        return studentService.findStudentById(id);
    }
    @GetMapping("/students/search/{student-name}")
    public List <StudentResponseDto> findStudentByName(
            @PathVariable("student-name") String name
    ) {
        return studentService.findStudentByName(name);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable ("student-id") Integer id
    ) {
        studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //@GetMapping("/hello")
//    public String sayHello() {
//        return "Hello from first controller";
//    }

//    @GetMapping("/hello-2")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public String sayHello2() {
//        return "Hello 2 from first controller";
//    }

//
//    @PostMapping("/post-order")
//    public String post(
//            @RequestBody Order order
//    ) {
//        return "Request accepted and order is : " + order.toString();
//    }
//
//    @PostMapping("/post-order-record")
//    public String post(
//            @RequestBody OrderRecord order
//    ) {
//        return "Request accepted and order is : " + order.toString();
//    }
//
//    //http://localhost:8080/hello/ibrahim
//    @GetMapping("/hello/{user-name}")
//    public String pathVar(
//            @PathVariable("user-name") String userName
//    ) {
//        return "My value = " + userName;
//    }
//
//    //http://localhost:8080/hello?param_name=paramvalue&param_name_2=value_2
//    @GetMapping("/hello")
//    public String paramVar(
//            @RequestParam("user-name") String userName,
//             @RequestParam("user-lastname") String userLastName
//    ) {
//        return "My value = " + userName + " " + userLastName;
//    }
}
