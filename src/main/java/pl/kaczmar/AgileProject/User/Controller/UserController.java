package pl.kaczmar.AgileProject.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kaczmar.AgileProject.User.Exception.AgileException;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Model.UserOutput;
import pl.kaczmar.AgileProject.User.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserInput userInput) throws AgileException {
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

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable (value = "id") Long id) throws AgileException {
        UserOutput userById = userService.getUserById(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUserById(@PathVariable (value = "id") Long id) throws AgileException {
        userService.removeUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
