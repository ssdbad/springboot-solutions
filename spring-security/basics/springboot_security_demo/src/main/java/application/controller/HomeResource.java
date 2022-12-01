package application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return ("<h1>Welcome Everyone</h1>");
    }

    @GetMapping("/user")
    public String userHome(){
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String adminHome(){
        return ("<h1>Welcome Admin</h1>");
    }
}
