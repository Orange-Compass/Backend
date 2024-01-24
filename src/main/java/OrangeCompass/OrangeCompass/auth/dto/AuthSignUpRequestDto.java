package OrangeCompass.OrangeCompass.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthSignUpRequestDto {
    private String email;
    private String username;
    private String password;
    private Date birth;
    private String type;
    private String login_type;
}
