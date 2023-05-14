package com.IssueTracker;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({ "/", "index" })
    public String index(){
        return "index";
    }

    @GetMapping("registration")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("registration")
    public String signupSave(@ModelAttribute("user") User user, BindingResult result, Model model) {
        User existing = userService.findByUsername(user.getUsername());
        if (existing != null) {
            model.addAttribute("error", "There is already an user with the same username");
            return "registration";
        }

        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("secret")
    public String secret() {
        return "secret";
    }

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }

}
