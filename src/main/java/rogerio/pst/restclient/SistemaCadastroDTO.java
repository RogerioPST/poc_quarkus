package rogerio.pst.restclient;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SistemaCadastroDTO implements Serializable {

	private static final long serialVersionUID = 8764619656762023447L;

	private String nome;

	private String tipoPessoa;

	private List<String> segmentoDTO;

}
