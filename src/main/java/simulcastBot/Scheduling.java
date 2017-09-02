package simulcastBot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Scheduling {
	
	private final ScheduledExecutorService fScheduler;
	private static final int NUM_THREADS = 1;
	long startTime;
	private static final long INITIAL_DELAY = -1;
	Record currRecord;
	private static final boolean DONT_INTERRUPT_IF_RUNNING = false;
	private ConnectingDiscord cDiscord;
	
	
	
	public Scheduling(Record currRecord, ConnectingDiscord cDiscord) {
		this.cDiscord = cDiscord;
		
		this.currRecord = currRecord;
		fScheduler = Executors.newScheduledThreadPool(NUM_THREADS); 
		startTime = currRecord.getSecondsUntil();
		
		System.out.println("Start of difference");
	    System.out.println(startTime);
	}
	
	void activateAlarmThenStop(){
	    Runnable soundAlarmTask = new displayInfo();
	    ScheduledFuture<?> soundAlarmFuture = fScheduler.schedule(
	      soundAlarmTask, startTime, TimeUnit.SECONDS
	    );
	    
	    // supposed to do stopAlarmTask here but i don't want this to ever stop.
	    // not sure what to do.
	    // I think i'm going to stop a few seconds after the action is to be performed!
	    
	    Runnable stopAlarm = new StopAlarmTask(soundAlarmFuture);
	    // stop after startTime + 10 seconds?
	    fScheduler.schedule(stopAlarm, startTime+10, TimeUnit.SECONDS);
	    
	  }
	
	
	
	//equivalent of soundAlarmtask
	private final class displayInfo implements Runnable{
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Title: " + currRecord.getTitle());
			System.out.println("IMAGE LINK?: " + currRecord.getLocation());
			cDiscord.displayAnnouncement(currRecord.getTitle(), currRecord.getLocation(), currRecord.getDescription());
			
		}
		
	}
	
	private final class StopAlarmTask implements Runnable {
	    StopAlarmTask(ScheduledFuture<?> aSchedFuture){
	      fSchedFuture = aSchedFuture;
	    }
	    @Override public void run() {
	      log("Stopping alarm.");
	      fSchedFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
	      /* 
	       Note that this Task also performs cleanup, by asking the 
	       scheduler to shutdown gracefully. 
	      */
	      fScheduler.shutdown();
	      // getting infite loop and still trying to get 1st event
	      ConnectingCalendar calendar = new ConnectingCalendar(cDiscord);
	    }
	    private ScheduledFuture<?> fSchedFuture;
	    
	  }
	
	private static void log(String aMsg){
	    System.out.println(aMsg);
	  }
}
