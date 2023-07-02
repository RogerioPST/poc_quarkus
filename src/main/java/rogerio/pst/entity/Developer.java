package rogerio.pst.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import rogerio.pst.annotation.JvmLanguage;


public class Developer {
	
	@JvmLanguage
	@NotBlank
	@JsonProperty("favorite-language")
	private String favoriteLanguage;

}
