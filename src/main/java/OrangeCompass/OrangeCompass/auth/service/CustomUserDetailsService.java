package OrangeCompass.OrangeCompass.auth.service;

import OrangeCompass.OrangeCompass.config.exception.BaseRuntimeException;
import OrangeCompass.OrangeCompass.config.response.BaseResponseStatus;
import OrangeCompass.OrangeCompass.user.domain.User;
import OrangeCompass.OrangeCompass.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static OrangeCompass.OrangeCompass.config.response.BaseResponseStatus.INVALID_USER;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(username);
        //TODO:예외처리
        return createUserDetails(user);
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴(authenticate.getName() -> Email)
    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getType().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
