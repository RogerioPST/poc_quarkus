package rogerio.pst.listener;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@JBossLog
public class ApplicationEventListener {
/*
 * Observe the io.quarkus.runtime.StartupEvent and the io.quarkus.runtime.Shut
downEvent. During application startup, Quarkus will fire the StartupEvent; and dur‐
ing shutdown, the ShutdownEvent, 
 */
	void onStart(@Observes StartupEvent event) {
		log.info("Application starting...");
	}

	@Named()
	void onStop(@Observes ShutdownEvent event) {
		log.info("Application shutting down...");
	}
	
}
