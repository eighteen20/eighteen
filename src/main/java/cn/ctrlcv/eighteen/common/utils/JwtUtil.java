package cn.ctrlcv.eighteen.common.utils;

import cn.ctrlcv.eighteen.common.model.UserToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Class Name: JwtUtil
 * Class Description: Jwt token 工具类
 *
 * @author liujm
 * @date 2023-04-24
 */
@SuppressWarnings("unused")
public class JwtUtil {
    private static final String SECRET_KEY = "cnctrlcveighteen121515155519759633";
    private static final long EXPIRATION_TIME = 3 * 60 * 60 * 1000;

    private static final int CLAIMS_INIT_CAPACITY = 3;

    public static String generateToken(UserToken userToken) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Map<String, Object> claims = new HashMap<>(CLAIMS_INIT_CAPACITY);
        claims.put("userId", userToken.getUserId());
        claims.put("openId", userToken.getOpenId());
        claims.put("nickname", userToken.getNickname());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException  | IllegalArgumentException e) {
            return false;
        }
    }

    public static String refreshToken(String token) {
        UserToken userToken = getObject(token);

        return generateToken(userToken);
    }

    private static UserToken getObject(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        UserToken userToken = new UserToken();
        userToken.setUserId((Integer) claims.get("userId"));
        userToken.setOpenId((String) claims.get("openId"));
        userToken.setNickname((String) claims.get("nickname"));
        return userToken;
    }

    public static Integer getUserIdFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return (Integer) claims.get("userId");
    }

    public static UserToken getUserFromToken(String token) {
        return getObject(token);
    }

}
