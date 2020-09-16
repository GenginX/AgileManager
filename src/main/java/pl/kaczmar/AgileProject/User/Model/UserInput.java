package pl.kaczmar.AgileProject.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import pl.kaczmar.AgileProject.User.Exception.*;

import java.time.LocalDate;

@ToString
@Getter
public class UserInput {

    private String login;
    private String password;
    @JsonIgnore
    private LocalDate createDate = LocalDate.now();

    public UserInput(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
