package rogerio.pst.restclient;

import java.io.Serializable;
import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public interface RestReturnBuilder<T> extends Serializable {

	public RestReturnBuilder<T> success();

	public RestReturnBuilder<T> success(T retorno);

	public RestReturnBuilder<T> success(List<T> retornoList);

	public RestReturnBuilder<T> warn(String message);

	public RestReturnBuilder<T> error(String message);

	public RestReturnBuilder<T> appendRetorno(T retorno);

	public RestReturnBuilder<T> appendRetornoList(List<T> retornoList);

	public RestReturnBuilder<T> appendMessageError(String message);

	public RestReturnBuilder<T> appendMessageWarn(String message);

	public RestReturnBuilder<T> appendTotalLinhas(Integer totalLinhas);

	public RestReturnBuilder<T> appendHttpStatus(Status httpStatus);

	public Response build();

}
