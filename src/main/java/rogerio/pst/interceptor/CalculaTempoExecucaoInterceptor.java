package rogerio.pst.interceptor;



import java.time.Duration;
import java.time.Instant;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.jbosslog.JBossLog;

@CalculaTempoExecucao
@Interceptor
@JBossLog
public class CalculaTempoExecucaoInterceptor {
 //static List<Event> events = new ArrayList<>();
 
 @AroundInvoke
 public Object logEvent(InvocationContext ctx) throws Exception {
	 Instant startT = Instant.now();
	 log.info("antes de executar o metodo: " + ctx.getMethod().getName());
	 
	 
	 try {
		 return ctx.proceed();		 
	 } finally {		 
		 log.info("### TEMPO exec metodo: " + ctx.getMethod().getName() + " :: " + Duration.between(startT, Instant.now()).toMillis());
	 }
	 
	 
 //events.add(new Event(ctx.getMethod().getName(),  Arrays.deepToString(ctx.getParameters())));
 }
}



