package pl.kaczmar.AgileProject.User.Service;

import org.springframework.stereotype.Service;
import pl.kaczmar.AgileProject.User.Model.UserInput;

public interface UserService {

    void createUserByService(UserInput userInput);

}
