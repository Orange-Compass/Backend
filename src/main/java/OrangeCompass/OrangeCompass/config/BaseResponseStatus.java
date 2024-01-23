package OrangeCompass.OrangeCompass.config;

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
    MATCH_NOT_FINISHED(false, 3063, "아직 종료되지 않은 경기입니다."),

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