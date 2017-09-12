package simulcastBot.discord;

import java.util.Collection;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/*
 * Description: Listens for commands, all use ++ as a prefix.
 * Author: Seal
 */
public class CommandsListener implements MessageCreateListener {

	private ChannelWizard channelWizard;

	public CommandsListener(ChannelWizard channelWizard) {
		this.channelWizard = channelWizard;
	}

	public void onMessageCreate(DiscordAPI api, Message message) {
		// check the content of the message
		if (message.getContent().equalsIgnoreCase("ping"))
		{
			// reply to the message
			message.reply("pong");
		}

		String currID = "";

		Collection<Role> userRoles = (message.getAuthor().getRoles(message.getChannelReceiver().getServer()));

		for (Role currRole : userRoles)
		{
			if (currRole.getName().equals("Simulcast"))
			{
				if (message.getContent().equalsIgnoreCase("++room"))
				{

					outputAnnouncement("Room is Open", "https://imgur.com/oFDj1cn.png",
							"Link to room: https://www.rabb.it/seal308");
				}

				if (message.getContent().equalsIgnoreCase("++channel"))
				{
					channelWizard.turnOn(message);
				}
			}
		}

	}

	public void outputAnnouncement(String title, String image, String description) {

		Display display = new Display(channelWizard);
		display.outputDisplay(title, image, description);
	}

}
