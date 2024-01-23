package OrangeCompass.OrangeCompass.auth.controller;

import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpResponseDto;
import OrangeCompass.OrangeCompass.auth.service.AuthService;
import OrangeCompass.OrangeCompass.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse<AuthSignUpResponseDto> signUp(@RequestBody AuthSignUpRequestDto authSignUpRequestDto) {

        return new BaseResponse<AuthSignUpResponseDto>(authService.authSignUp(authSignUpRequestDto));
    }
}
