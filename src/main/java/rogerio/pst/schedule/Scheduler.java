package rogerio.pst.schedule;

import java.util.concurrent.atomic.AtomicInteger;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Scheduler {
	private AtomicInteger count = new AtomicInteger();

	int get() {
		return count.get();
	}

	@Scheduled(every = "5s")
	public void fiveSeconds(ScheduledExecution execution) {
		count.incrementAndGet();
		System.out.println("Running counter: 'fiveSeconds'. Next fire: " + execution.getTrigger().getNextFireTime());
	}
	
	@Scheduled(every = "5s")
	public void fiveSeconds2() {
		count.incrementAndGet();
		System.out.println("Running counter: 'fiveSeconds'. Next fire: ");
	}
}
