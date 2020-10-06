package reddit.example.simpleforumgmail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reddit.example.simpleforumgmail.models.ConfirmationToken;
import reddit.example.simpleforumgmail.models.User;
import reddit.example.simpleforumgmail.repository.ConfirmationTokenRepository;

import reddit.example.simpleforumgmail.services.EmailSenderService;

import reddit.example.simpleforumgmail.services.UserServiceM;

@Controller

public class UserRegistrationController {

    @Autowired
    private UserServiceM userService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    public UserRegistrationController(UserServiceM userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")

    public String showRegistrationForm(Model model, User user) {
        model.addAttribute("user", user);
        return "newRegistration";
    }

    @PostMapping("/registrationn")
    public ModelAndView registerUser(ModelAndView modelAndView, User user) {

        User existingUser = userService.findByEmailIgnoreCase(user.getEmail());
        if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error2");
        } else {

            User u = userService.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(u);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("redditexampleapplication@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:9091/confirm-account?token=" + confirmationToken.getConfirmationtoken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("email", user.getEmail());

            modelAndView.setViewName("successfulregistration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationtoken(confirmationToken);

        if (token != null) {
            User user = userService.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userService.saveUsers(user);

            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error2");
        }

        return modelAndView;
    }


}

