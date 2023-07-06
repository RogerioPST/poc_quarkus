package rogerio.pst.restclient;

import java.time.ZonedDateTime;
import java.util.Base64;

import io.vertx.core.json.JsonObject;

public final class TokenUtils {

	private TokenUtils() {
	}

	/**
	 * 
	 * Decodifica o token
	 *
	 * 
	 * 
	 * @param token
	 * 
	 * @return
	 * 
	 */

	public static JsonObject decodeJWT(String token) {

		int idx1 = token.indexOf(".");

		String novoToken = token.substring(idx1 + 1, token.length() - 1);

		int idx2 = novoToken.indexOf(".");

		novoToken = novoToken.substring(0, idx2);

		String decoded = new String(Base64.getUrlDecoder().decode(novoToken));

		return new JsonObject(decoded);

	}

	/**
	 * 
	 * Verifica se o token esta expirado
	 *
	 * 
	 * 
	 * @param token
	 * 
	 * @return
	 * 
	 */

	public static boolean isTokenExpirado(String token) {

		JsonObject decodeToken = decodeJWT(token);

		return decodeToken.getLong("exp") <= ZonedDateTime.now().toEpochSecond();

	}

}
