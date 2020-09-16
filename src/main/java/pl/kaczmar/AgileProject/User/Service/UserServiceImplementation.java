package pl.kaczmar.AgileProject.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmar.AgileProject.User.Entity.UserEntity;
import pl.kaczmar.AgileProject.User.Exception.AgileException;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Model.UserOutput;
import pl.kaczmar.AgileProject.User.Repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    public static final String NO_USER_FOUND_FOR_GIVEN_ID = "NO USER FOUND FOR GIVEN ID";
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUserByService(UserInput userInput) {
        userRepository.save(
                new UserEntity(userInput.getLogin(), userInput.getPassword(), userInput.getCreateDate()));
    }

    public List<UserOutput> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(e -> e.convertToUserOutput())
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserById(Long id) throws AgileException {
        if(checkIfUserWithThisIdExists(id)){
            userRepository.delete(getUserEntityById(id));
        }
    }

    @Override
    public UserOutput getUserById(Long id) throws AgileException {
        return getUserEntityById(id).convertToUserOutput();
    }

    private boolean checkIfUserWithThisIdExists(Long id){
        return userRepository.existsById(id);
    }

    private UserEntity getUserEntityById(Long id) throws AgileException {
        if(checkIfUserWithThisIdExists(id)){
            Optional<UserEntity> byId = userRepository.findById(id);
            return byId.get();
        }
        throw new AgileException(NO_USER_FOUND_FOR_GIVEN_ID);
    }
}
