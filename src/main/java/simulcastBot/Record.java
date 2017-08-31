package simulcastBot;

import java.util.concurrent.TimeUnit;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

public class Record {
	// we just need the title, time, where, and description
	
	private String title;
	private String location;
	private String description;
	private long secondsUntil;
	private DateTime start;
	
	public Record(Event event)
	{
		start = event.getStart().getDateTime();
		title = event.getSummary();
		location = event.getLocation();
		secondsUntil = TimeUnit.MILLISECONDS.toSeconds(start.getValue()- System.currentTimeMillis());
		description = event.getDescription();
	}
	
	public long getSecondsUntil()
	{
		return secondsUntil;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String toString() { 
	    return "title: '" + title + "', secondsUntil: '" + secondsUntil + "', description: '" + description + "', location: '" + location + "'";
	} 

}
