package rogerio.pst.exception;

import java.io.IOException;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
public class CustomResponseExceptionMapper
 implements ResponseExceptionMapper<IOException> {
 @Override
 public IOException toThrowable(Response response) {
 return new IOException();
 }
 @Override
 public boolean handles(int status,
 MultivaluedMap<String, Object> headers) {
 return status >= 400 && status < 500;
 }
}
