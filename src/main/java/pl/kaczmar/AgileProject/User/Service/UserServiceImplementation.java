package pl.kaczmar.AgileProject.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmar.AgileProject.User.Entity.UserEntity;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUserByService(UserInput userInput) {
        userRepository.save(
                new UserEntity(userInput.getLogin(), userInput.getPassword(), userInput.getUserType(), userInput.getCreateDate()));
    }
}
