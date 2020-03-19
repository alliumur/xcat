package pl.rmv.xcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.rmv.xcat.model.User;
import pl.rmv.xcat.service.UserService;
import pl.rmv.xcat.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model){
        if (isAuthenticated()) {
            return "redirect:/welcome";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult result, RedirectAttributes redirectAttributes){
        userValidator.validate(userForm, result);
        if(result.hasErrors()) return "registration";

        userService.save(userForm);
        redirectAttributes.addFlashAttribute("message", "Registration successful!");
        return "redirect:/login";
    }

    public boolean isAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

    }

    @GetMapping("/login")
    public String login(Model model, String error){
        if (isAuthenticated()) {
            return "redirect:/welcome";
        }

        if(error != null) {
            model.addAttribute("error", "Username or password is invalid.");
        }
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model){
        return "welcome";
    }
}
