package com.Ibrahim.example;


import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {

    //@GetMapping("/hello")
    public String sayHello() {
        return "Hello from first controller";
    }

//    @GetMapping("/hello-2")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public String sayHello2() {
//        return "Hello 2 from first controller";
//    }

    @PostMapping("/post")
    public String post(
           @RequestBody String message
    ) {
        return "Request accepted and message is : " + message;
    }
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
