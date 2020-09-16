package pl.kaczmar.AgileProject.User.Service;

import org.springframework.stereotype.Service;
import pl.kaczmar.AgileProject.User.Entity.UserEntity;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Model.UserOutput;

import java.util.List;

public interface UserService {

    void createUserByService(UserInput userInput);
    List<UserOutput> getAllUsers();

}
