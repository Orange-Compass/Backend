package OrangeCompass.OrangeCompass.auth.controller;

import OrangeCompass.OrangeCompass.auth.dto.AuthSignInRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpResponseDto;
import OrangeCompass.OrangeCompass.auth.service.AuthService;
import OrangeCompass.OrangeCompass.config.jwt.JwtTokenDto;
import OrangeCompass.OrangeCompass.config.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse<AuthSignUpResponseDto> signUp(@RequestBody AuthSignUpRequestDto authSignUpRequestDto) {
        return new BaseResponse<AuthSignUpResponseDto>(authService.signUp(authSignUpRequestDto));
    }

    @PostMapping("/sign-in")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse<JwtTokenDto> signIn(@RequestBody AuthSignInRequestDto authSignInRequestDto){
        log.debug(authSignInRequestDto.getEmail());
        return new BaseResponse<JwtTokenDto>(authService.signIn(authSignInRequestDto));
    }
}
