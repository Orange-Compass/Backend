package OrangeCompass.OrangeCompass.config.jwt;

import OrangeCompass.OrangeCompass.config.SecurityUtil;
import OrangeCompass.OrangeCompass.config.response.BaseResponse;
import OrangeCompass.OrangeCompass.config.response.BaseResponseStatus;
import OrangeCompass.OrangeCompass.user.domain.User;
import OrangeCompass.OrangeCompass.user.domain.UserStatus;
import OrangeCompass.OrangeCompass.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //필요한 권한이 없이 접근하려 할때 403
        log.debug("JwtAccessDeniedHandler handle");
        log.debug("JwtAccessDeniedHandler request : "+request.toString());
        log.debug("JwtAccessDeniedHandler response : "+response.toString());
        try{
            User user = this.userRepository.findByEmail(SecurityUtil.getCurrentUserEmail());
            log.debug(user.getEmail());
            if(user == null){
                BaseResponse res = new BaseResponse(BaseResponseStatus.INVALID_USER);
                response.getWriter().println(
                        mapper.writeValueAsString(res)
                );
            }
            else if(user.getStatus() == UserStatus.INACTIVE.getStatus()){
                BaseResponse res = new BaseResponse(BaseResponseStatus.INACTIVE_USER);
                response.getWriter().println(
                        mapper.writeValueAsString(res)
                );
            }


        } catch (Exception e){
            throw e;
        }

    }
}