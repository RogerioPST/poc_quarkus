package rogerio.pst.restclient;

import org.eclipse.microprofile.config.ConfigProvider;

// n foi usado ConfigProperty, pq os valores sao usados em classes q n sao gerenciadas pelo CDI, o q retornaria seus valores nulos.

public class SystemProperties {
	
	public static String TOKEN_CLIENT_ID = ConfigProvider.getConfig().getOptionalValue("TOKEN_CLIENT_ID", String.class).orElse("cli-ser-sistema");	
	
	public static String TOKEN_CLIENT_SECRET = ConfigProvider.getConfig().getOptionalValue("TOKEN_CLIENT_SECRET", String.class).orElse("d5e5a6f6-6cfd-4785-ae2b-2c4cb8332083");
	
	public static String TWITCH_CLIENT_ID = ConfigProvider.getConfig().getOptionalValue("TWITCH_CLIENT_ID", String.class).orElse("client-id");
	
	public static String TWITCH_CLIENT_SECRET = ConfigProvider.getConfig().getOptionalValue("TWITCH_CLIENT_SECRET", String.class).orElse("client-secret");
	
	public static String CADASTRO_API_KEY = ConfigProvider.getConfig().getOptionalValue("CADASTRO_API_KEY", String.class).orElse("l76ba2e6475d69402aa8f1366455e10234");
	
	public static Boolean LOG_TRANSACAO_ENABLED = ConfigProvider.getConfig().getOptionalValue("LOG_TRANSACAO_ENABLED", Boolean.class).orElse(true);
	
	
	
	
	private SystemProperties() {
		
	}
	
	

}
