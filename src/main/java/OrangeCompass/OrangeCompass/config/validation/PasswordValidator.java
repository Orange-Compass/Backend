package OrangeCompass.OrangeCompass.config.validation;

import OrangeCompass.OrangeCompass.config.exception.BaseRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static OrangeCompass.OrangeCompass.config.response.BaseResponseStatus.INVALID_PASSWORD;

@RequiredArgsConstructor
public class PasswordValidator extends AbstractValidator{

    private final PasswordEncoder passwordEncoder;
    private final String srcPassword;
    private final String dstPassword;

    public static PasswordValidator of(PasswordEncoder passwordEncoder, String srcPassword, String dstPassword){
        return new PasswordValidator(passwordEncoder, srcPassword, dstPassword);
    }
    @Override
    public void validate() {
        if(!passwordEncoder.matches(dstPassword, srcPassword)){
            throw new BaseRuntimeException(INVALID_PASSWORD);
        }
    }
}
