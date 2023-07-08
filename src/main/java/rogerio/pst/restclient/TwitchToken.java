package rogerio.pst.restclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TwitchToken {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_in")
	private Integer expiresIn;	

	@JsonProperty("token_type")
	private String tokenType;
	
	

	public boolean isExpired() {

		//return TokenUtils.isTokenExpirado(this.getAccessToken());
		return false;

	}

	public boolean isValid() {

		//return !this.isExpired();
		return true;

	}

}
