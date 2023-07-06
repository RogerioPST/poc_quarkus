package rogerio.pst.restclient;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.quarkus.logging.Log;
import lombok.extern.jbosslog.JBossLog;
import net.minidev.json.JSONArray;

@JBossLog
public final class JsonUtil {
	static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	private JsonUtil() {

	}

	public static List<?> jsonArrayToList(JSONArray jsonList) {
		List<Object> lista = new ArrayList<>();

		jsonList.forEach(l -> {
			lista.add(l);
		});

		return lista;
	}

	public static String toJson(Object value) {
		String json = null;

		try {
			json = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			Log.error("**************** erro ao converter objeto em json");
			e.printStackTrace();
		}

		return json;
	}

	public static <T> T fromJson(String json, Class<T> valueType) {
		T obj = null;

		try {
			obj = mapper.readValue(json, valueType);
		} catch (JsonProcessingException e) {
			Log.error("**************** erro ao converter json em objeto ");
			e.printStackTrace();
		}

		return obj;
	}

}
