package pl.kaczmar.AgileProject.User.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class UserOutput {
    private String login;
    private Long id;
    private LocalDate createDate;

    public UserOutput(String login, Long id, LocalDate createDate) {
        this.login = login;
        this.id = id;
        this.createDate = createDate;
    }
}
