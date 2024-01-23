package OrangeCompass.OrangeCompass.config.jwt;

import OrangeCompass.OrangeCompass.config.response.BaseResponse;
import OrangeCompass.OrangeCompass.config.response.BaseResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static OrangeCompass.OrangeCompass.config.response.BaseResponseStatus.EXPIRED_ACCESS_JWT;
import static OrangeCompass.OrangeCompass.config.response.BaseResponseStatus.INVALID_ACCESS_JWT;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;
    // ㅇ
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        BaseResponseStatus baseResponseStatus = (BaseResponseStatus) request.getAttribute("exception");

        // 유효한 자격증명을 제공하지 않고 접근하려 할때
        if(baseResponseStatus == null || baseResponseStatus == INVALID_ACCESS_JWT) {
            this.setResponse(response, INVALID_ACCESS_JWT);
            return;
        }
        // 만료된 토큰
        if(baseResponseStatus == EXPIRED_ACCESS_JWT){
            this.setResponse(response, EXPIRED_ACCESS_JWT);
        }
    }

    private void setResponse(
            HttpServletResponse response,
            BaseResponseStatus baseResponseStatus
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        BaseResponse res = new BaseResponse(baseResponseStatus);
        response.getWriter().println(
            mapper.writeValueAsString(res)
        );
    }
}