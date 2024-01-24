package OrangeCompass.OrangeCompass.auth.service;

import OrangeCompass.OrangeCompass.auth.dto.AuthSignInRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpResponseDto;
import OrangeCompass.OrangeCompass.config.jwt.JwtTokenDto;
import OrangeCompass.OrangeCompass.config.jwt.JwtTokenProvider;
import OrangeCompass.OrangeCompass.config.validation.PasswordValidator;
import OrangeCompass.OrangeCompass.config.validation.ValidatorBucket;
import OrangeCompass.OrangeCompass.user.domain.User;
import OrangeCompass.OrangeCompass.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthSignUpResponseDto signUp(AuthSignUpRequestDto authSignUpRequestDto){
        User user = User.builder()
                .username(authSignUpRequestDto.getUsername())
                .birth(authSignUpRequestDto.getBirth())
                .email(authSignUpRequestDto.getEmail())
                .type(authSignUpRequestDto.getType())
                .loginType(authSignUpRequestDto.getLogin_type())
                .password(passwordEncoder.encode(authSignUpRequestDto.getPassword()))
                .build();

        userRepository.addNewUser(user);
        return new AuthSignUpResponseDto(user.getUsername(), user.getEmail());
    }

    public JwtTokenDto signIn(AuthSignInRequestDto authSignInRequestDto){
        // TODO:예외처리
        // DB에서 유저 확인
        User user = userRepository.findByEmail(authSignInRequestDto.getEmail());
        // Password 확인
        ValidatorBucket signInValidatorBucket = ValidatorBucket.of()
                .consistOf(new PasswordValidator(passwordEncoder, user.getPassword(), authSignInRequestDto.getPassword())
                );
        signInValidatorBucket.validate();
        log.debug(authSignInRequestDto.getEmail());
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = authSignInRequestDto.toAuthentication();
        log.debug(authenticationToken.toString());
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtTokenDto jwtTokenDto = jwtTokenProvider.generateTokenDto(authentication);

        return jwtTokenDto;
    }
}
