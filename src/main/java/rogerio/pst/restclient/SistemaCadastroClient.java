package rogerio.pst.restclient;

public interface SistemaCadastroClient {

	SistemaCadastroDTO getInfoByDocumento(final String documento) throws Exception;

}
