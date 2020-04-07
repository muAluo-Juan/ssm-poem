package utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import po.NormalUser;

public class JWTUtil {
	private static final long EXPIRE_TIME = 24 * 60 * 30 * 1000;
	private static final String secret="adfdsfjewafiojdsakfjsd";
	public static String generateToken(String username) {
		 try {
	            // 指定过期时间
	            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

	            Algorithm algorithm = Algorithm.HMAC256(secret);

	            return JWT.create()
	                    .withClaim("username", username)
	                    .withExpiresAt(date)
	                    .sign(algorithm);
	        } catch (UnsupportedEncodingException e) {
	            return null;
	        }
    }

    public static boolean validateToken(String token,String username) {
    	try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();

            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String getUsername(String token) {
    	return JWT.decode(token).getAudience().get(0);
    }
}
