package pl.kaczmar.AgileProject.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Model.UserOutput;
import pl.kaczmar.AgileProject.User.Service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserInput userInput){
        userService.createUserByService(userInput);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers(){
        List<UserOutput> allUsers = userService.getAllUsers();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(allUsers);
    }

}
