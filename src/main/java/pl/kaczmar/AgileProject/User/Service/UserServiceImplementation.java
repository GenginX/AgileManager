package pl.kaczmar.AgileProject.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmar.AgileProject.User.Entity.UserEntity;
import pl.kaczmar.AgileProject.User.Exception.AgileException;
import pl.kaczmar.AgileProject.User.Model.UserInput;
import pl.kaczmar.AgileProject.User.Model.UserOutput;
import pl.kaczmar.AgileProject.User.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    public static final String NO_USER_FOUND_FOR_GIVEN_ID = "NO USER FOUND FOR GIVEN ID";
    public static final String USER_NAME_VALIDATION = "USER WITH THIS NAME IS EXISTING, WE CAN'T ADD MORE";
    public static final String PASSWORD_IS_WEAK = "Please provide stronger password: length higher than 8, one big letter, one small letter, one digit, one special character";
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUserByService(UserInput userInput) throws AgileException {
        userNameValidation(userInput.getLogin());
        passwordValidation(userInput.getPassword());
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
        if (checkIfUserWithThisIdExists(id)) {
            userRepository.delete(getUserEntityById(id));
        }
    }

    @Override
    public UserOutput getUserById(Long id) throws AgileException {
        return getUserEntityById(id).convertToUserOutput();
    }

    private boolean checkIfUserWithThisIdExists(Long id) {
        return userRepository.existsById(id);
    }

    private UserEntity getUserEntityById(Long id) throws AgileException {
        if (checkIfUserWithThisIdExists(id)) {
            Optional<UserEntity> byId = userRepository.findById(id);
            return byId.get();
        }
        throw new AgileException(NO_USER_FOUND_FOR_GIVEN_ID);
    }

    private boolean userNameValidation(String name) throws AgileException {
        if (userRepository.existsByLogin(name)) {
            throw new AgileException(USER_NAME_VALIDATION);
        }
        return false;
    }

    private boolean passwordValidation(String password) throws AgileException {
        Pattern bigLetter = Pattern.compile("[A-Z]");
        Pattern smallLetter = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%^&*()_+\\[\\]{};',.:|<>?]");

        List<Matcher> matchers = new ArrayList<>();
        matchers.add(bigLetter.matcher(password));
        matchers.add(smallLetter.matcher(password));
        matchers.add(digit.matcher(password));
        matchers.add(special.matcher(password));

        if (password.length() < 8) {
            throw new AgileException(PASSWORD_IS_WEAK);
        }
        for (Matcher matcher : matchers) {
            if (!matcher.find()) {
                throw new AgileException(PASSWORD_IS_WEAK);
            }
        }
        return true;
    }
}
