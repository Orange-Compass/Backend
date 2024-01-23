package OrangeCompass.OrangeCompass.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AuthSignUpRequestDto {
    private String email;
    private String username;
    private String password;
    private Date birth;
    private String type;
    private String login_type;
}
