package OrangeCompass.OrangeCompass.config.exception;

import OrangeCompass.OrangeCompass.config.response.BaseResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class BaseRuntimeException extends RuntimeException{
    private final BaseResponseStatus baseResponseStatus;
}
