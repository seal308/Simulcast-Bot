package simulcastBot.calendar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.api.client.auth.oauth2.Credential;
// I had issues with these 2 but now fixed from here: 
// https://stackoverflow.com/questions/13662562/maven-usage-of-google-api
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import simulcastBot.discord.ConnectingDiscord;

/*
 * Description:
 * 		Connects to google calendar, gets events, and sends the next event to scheduling
 * 		Most of the connection code is taken from the Google API
 * 		Moved main code to this class's constructor.
 * 		Made getNextEvent method and modified relevant API code to meet needs.
 * Author: Seal
 */
public class ConnectingCalendar {

	private ConnectingDiscord cDiscord;

	/** Application name. */
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/calendar-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/**
	 * Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials at
	 * ~/.credentials/calendar-java-quickstart
	 */
	private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR_READONLY);

	static
	{
		try
		{
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t)
		{
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = ConnectingCalendar.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Calendar client service.
	 * 
	 * @return an authorized Calendar client service
	 * @throws IOException
	 */
	public static com.google.api.services.calendar.Calendar getCalendarService() throws IOException {
		Credential credential = authorize();
		return new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	// replaced main method with heading of constructor
	// contents are all in a try catch statement
	// we need to fix this later, adding to github
	public ConnectingCalendar(ConnectingDiscord cDiscord) {
		this.cDiscord = cDiscord;

		// Build a new authorized API client service.
		// Note: Do not confuse this class with the
		// com.google.api.services.calendar.model.Calendar class.
		com.google.api.services.calendar.Calendar service;
		try
		{
			service = getCalendarService();

			getNextEvent(service);

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getNextEvent(com.google.api.services.calendar.Calendar service) {
		DateTime now = new DateTime(System.currentTimeMillis());
		DateTime nowPlusTen = new DateTime(System.currentTimeMillis() + 10000);
		System.out.println(now.getValue());
		System.out.println(nowPlusTen.getValue());
		System.out.println("I luv soup: " + service.getClass().getName());
		try
		{
			Events events = service.events().list("primary").setMaxResults(20).setTimeMin(now).setOrderBy("startTime")
					.setSingleEvents(true).execute();

			// We might need to remove the maxResults if extreme cases
			// Google API has no lower bounds for start time only upper bounds
			// timeComparison below fixes the issue

			List<Event> items = events.getItems();
			if (items.size() == 0)
			{
				// this should never happen because we put a event in the far future like 2020
				// if this does happen and someone makes a event, that even will never pick up
				// therefore i'm adding this to the issues on github
				System.out.println("No upcoming events found.");
			} else
			{

				Event currEvent = null;
				long timeComparison;

				System.out.println("Upcoming events");
				for (Event event : items)
				{
					DateTime start = event.getStart().getDateTime();

					timeComparison = start.getValue() - System.currentTimeMillis();

					if (currEvent == null && start != null && timeComparison >= 0)
					{
						System.out.println("WE IN THE COMPARATORS!!!!");
						currEvent = event;
					}

					if (start == null)
					{
						start = event.getStart().getDate();
					}

					System.out.printf("%s (%s)\n", event.getSummary(), start);
					System.out.println("location: " + event.getLocation());
					System.out.println(TimeUnit.MILLISECONDS.toSeconds(start.getValue() - System.currentTimeMillis()));

				}

				if (currEvent != null)
				{
					Record curRecord = new Record(currEvent);
					System.out.println(curRecord.toString());
					Scheduling scheduleEvent = new Scheduling(curRecord, cDiscord);
					scheduleEvent.activateAlarmThenStop();
				} else
				{
					// maybe throw an error?
					// or do a while loop around the for loop that gets list of events to increment
					// counter by 10
					// so range goes from 10 to 20 to 30 and so on if the increment is 10
					System.out.println("The retrieved list of events are invalid");
				}

			}

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
