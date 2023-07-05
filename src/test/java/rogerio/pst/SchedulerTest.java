package rogerio.pst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import rogerio.pst.schedule.Scheduler;

@QuarkusTest
public class SchedulerTest {
	
	@Inject
	Scheduler scheduler;
	
	@Test
	public void schedule() {				
		scheduler.fiveSeconds2();
		assertEquals(1, 1);
	}

}
