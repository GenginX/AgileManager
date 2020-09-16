package pl.kaczmar.AgileProject.User.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kaczmar.AgileProject.User.Model.UserOutput;
import pl.kaczmar.AgileProject.User.Model.UserType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String userType;
    private LocalDate createDate;

    public UserEntity(String login, String password, UserType userType, LocalDate createDate) {
        this.login = login;
        this.password = password;
        this.userType = userType.getName();
        this.createDate = createDate;
    }

    public UserOutput convertToUserOutput(){
        return new UserOutput(login, id, createDate, userType);
    }
}
