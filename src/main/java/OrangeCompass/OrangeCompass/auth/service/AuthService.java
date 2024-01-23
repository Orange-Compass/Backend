package OrangeCompass.OrangeCompass.auth.service;

import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpRequestDto;
import OrangeCompass.OrangeCompass.auth.dto.AuthSignUpResponseDto;
import OrangeCompass.OrangeCompass.user.domain.User;
import OrangeCompass.OrangeCompass.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthSignUpResponseDto authSignUp(AuthSignUpRequestDto authSignUpRequestDto){
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
}
