package rogerio.pst.restclient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginacaoDTO {

	@Schema(title = "Total de Registros", example = "100")
	private Integer count;

	@Schema(title = "Máximo de registros por pesquisa")
	private Integer limit;

	@Schema(title = "Registro inicial onde iniciar a pesquisa")
	private Integer offset;

	public PaginacaoDTO(Integer limit, Integer offset) {
		this.limit = limit;
		this.offset = offset;
	}

	public PaginacaoDTO(Integer count) {
		this.count = count;
	}

}
