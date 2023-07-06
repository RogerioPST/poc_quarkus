package rogerio.pst.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class RestReturnDTO<T> extends ResponseConfigResource {
	
	private Boolean temErro;
	private List<String> msgsErro;

}
