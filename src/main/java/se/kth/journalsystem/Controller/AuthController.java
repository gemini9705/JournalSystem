package se.kth.journalsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.journalsystem.model.User;
import se.kth.journalsystem.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Registrering av nya användare
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Inloggning av användare
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean loginSuccessful = userService.validateUser(user.getUsername(), user.getPassword());
        return loginSuccessful ? ResponseEntity.ok("Inloggning lyckades") : ResponseEntity.status(401).body("Felaktigt användarnamn eller lösenord");
    }
}
