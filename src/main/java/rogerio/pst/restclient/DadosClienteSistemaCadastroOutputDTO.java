package rogerio.pst.restclient;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Setter

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class DadosClienteSistemaCadastroOutputDTO {

	private String dadosBasicos;

	private List<String> carteira = new ArrayList<>();

}