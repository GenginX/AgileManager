package pl.kaczmar.AgileProject.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import pl.kaczmar.AgileProject.User.Exception.*;

import java.time.LocalDate;

@ToString
@Getter
public class UserInput {

    public static final String WRONG_USER_TYPE_PROVIDED = "WRONG USER TYPE PROVIDED";
    private String login;
    private String password;
    private UserType userType;
    @JsonIgnore
    private LocalDate createDate = LocalDate.now();

    public UserInput(String login, String password, String userType) throws AgileException {
        this.login = login;
        this.password = password;
        this.userType = convertStringToUserType(userType);
    }

    private UserType convertStringToUserType(String userType) throws AgileException {
        UserType[] values = UserType.values();
        for (UserType value : values){
            if(value.getName().toLowerCase().equals(userType.toLowerCase())){
                return value;
            }
            else{
                throw new AgileException(WRONG_USER_TYPE_PROVIDED);
            }
        }
        return null;
    }

}
