package OrangeCompass.OrangeCompass.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthSignUpResponseDto {
    private String userName;
    private String email;
}
