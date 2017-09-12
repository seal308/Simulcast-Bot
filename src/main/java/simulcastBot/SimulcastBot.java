package simulcastBot;

import simulcastBot.calendar.ConnectingCalendar;
import simulcastBot.discord.ConnectingDiscord;

/*
 * Description: The main class and starting point.
 * Author: Seal
 */
public class SimulcastBot {

	public static void main(String[] args) {

		ConnectingDiscord discordConnect = new ConnectingDiscord(
				"MzQwNTA4MzY4MjU3MTU1MDc1.DFzixg.tZCKhfe7Lf8X9uY32OoDpe_DAfE");
		ConnectingCalendar calendarConnect = new ConnectingCalendar(discordConnect);
		System.out.println("hello");

	}

}