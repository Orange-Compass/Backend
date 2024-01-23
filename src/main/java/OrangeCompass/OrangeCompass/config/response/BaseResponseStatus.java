package OrangeCompass.OrangeCompass.config.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    /**
     * 2000 : 요청 성공
     */
    SUCCESS(true, 2000, "요청에 성공하였습니다."),

    /**
     * 3000 : 클라이언트 에러
     */
    INVALID_ACCESS_JWT(false, 3000, "유효하지 않은 토큰 입니다."),
    EXPIRED_ACCESS_JWT(false, 3001, "만료된 토큰 입니다."),
    INACTIVE_USER(false, 3010, "탈퇴한 유저입니다."),
    INVALID_USER(false, 3011, "존재하지 않는 유저입니다."),

    /**
     * 4000 : 서버 에러
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}