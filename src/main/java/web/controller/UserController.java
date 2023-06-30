package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all users:
    @RequestMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users.html";
    }



    /*
    @RequestMapping("/hello")
    public String getUser(@RequestParam(required = false) String name, Model model) { // igy az alap John Doe lesz
    //public String getUser(@RequestParam(required = true) String name, Model model) { //http://localhost:8080/SPRING-Web/hello?name=Andris**
        model.addAttribute("user", userService.getUser(name));
        //return "myView";
        return "hello.html"; //thymeleaf
    }
    */
    //** HTTP ERROR 400 Required parameter 'name' is not present. Igy a hello után muszáj neki nevet adni!
}
