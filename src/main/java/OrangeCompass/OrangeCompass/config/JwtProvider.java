package OrangeCompass.OrangeCompass.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;

public class JwtProvider {
    private static final Long accessTokenValidTime = Duration.ofMinutes(30).toMillis(); // expire time 30 min
    private static final Long refreshTokenValidTime = Duration.ofDays(14).toMillis(); // expire time 2 weeks

    // 회원 정보 조회
    public static Long getUserId(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    // 토큰 유효 및 만료 확인
    public static boolean isExpired(String token, String secretKey) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                ;
        return false;
    }

    // refresh 토큰 확인
    public static boolean isRefreshToken(String token, String secretKey) {

        Header header = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getHeader();

        if (header.get("type").toString().equals("refresh")) {
            return true;
        }
        return false;
    }

    // access 토큰 확인
    public static boolean isAccessToken(String token, String secretKey) {

        Header header = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getHeader();

        if (header.get("type").toString().equals("access")) {
            return true;
        }
        return false;
    }

    // access 토큰 생성
    public static String createAccessToken(Long userId, String secretKey) {
        return createJwt(userId, secretKey, "access", accessTokenValidTime);
    }

    // refresh 토큰 생성
    public static String createRefreshToken(Long userId, String secretKey) {
        return createJwt(userId, secretKey,"refresh", refreshTokenValidTime);
    }

    public static String createJwt(Long userId, String secretKey, String type, Long tokenValidTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setHeaderParam("type", type)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
                ;
    }
}
