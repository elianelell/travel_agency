package fr.lernejo.travelsite;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserRepository userRepository =  new UserRepository();

    @PostMapping(value = "/api/inscription", consumes = {"application/json"})
    public void createUser(@RequestBody User user) {
        userRepository.addUser(user);
    }
}
