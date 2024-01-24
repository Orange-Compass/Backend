package OrangeCompass.OrangeCompass.user.controller;

import OrangeCompass.OrangeCompass.auth.dto.AuthSignInRequestDto;
import OrangeCompass.OrangeCompass.config.jwt.JwtTokenDto;
import OrangeCompass.OrangeCompass.config.response.BaseResponse;
import OrangeCompass.OrangeCompass.config.response.BaseResponseStatus;
import OrangeCompass.OrangeCompass.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/temp")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse<String> temp(){
        return new BaseResponse<String>("Hello World!");
    }
}
