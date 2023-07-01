package web.controller;


import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import web.model.request.CreateUserRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.model.request.CreateUserRequest;
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
        return "users";
    }

    // get user by ID:
    @RequestMapping("/user/{id}")
    //@RequestMapping("/user")
    public String getUserById(Model model, @PathVariable long id){
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping("/addUser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "add_user";
    }

    @RequestMapping("/user")
    public String addUser(CreateUserRequest request) {
        User createdUser = userService.createUser(request);
        return "redirect:/SPRING-Web/user/" + createdUser.getId();
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
