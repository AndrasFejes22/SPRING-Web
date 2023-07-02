package web.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    @GetMapping("/users")
    public String getAllUsers(Model model, @CookieValue(required = false) Long visitedUserId ){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("highlighted", visitedUserId);
        return "users";
    }

    // get user by ID:
    /*
    @RequestMapping("/user/{id}")
    //@RequestMapping("/user")
    public String getUserById(Model model, @PathVariable long id){
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

     */


    // get user by ID_2: (highlighted stuff)
    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable long id, HttpServletResponse response){
        User user = userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);

        if(user != null){
            Cookie visitedUserIdCookie = createVisitedUserIdCookie(user.getId());
            response.addCookie(visitedUserIdCookie);
        }
        return "user";
    }

    private Cookie createVisitedUserIdCookie(Long userId){
        Cookie cookie = new Cookie("visitedUserId", userId.toString());
        cookie.setPath("/SPRING-Web/");
        //cookie.setDomain("localhost");
        return cookie;
    }



    @RequestMapping("/addUser")
    public String addUserPage(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "add_user";
    }

    //@RequestMapping(value = "/user", method = RequestMethod.POST) //ilyen is van
    @PostMapping("/user") //ez a legcelravezetobb
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
