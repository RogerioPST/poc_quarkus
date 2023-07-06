package rogerio.pst.restclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

@NoArgsConstructor

@AllArgsConstructor

public class TokenSSO {

	@JsonProperty("access_token")

	private String accessToken;

	@JsonProperty("expires_in")

	private Integer expiresIn;

	@JsonProperty("refresh_expires_in")

	private Integer refreshExpiresIn;

	@JsonProperty("refresh_token")

	private String refreshToken;

	@JsonProperty("token_type")

	private String tokenType;

	@JsonProperty("session_state")

	private String sessionState;

	@JsonProperty("scope")

	private String scope;

	public boolean isExpired() {

		return TokenUtils.isTokenExpirado(this.getAccessToken());

	}

	public boolean isValid() {

		return !this.isExpired();

	}

}
