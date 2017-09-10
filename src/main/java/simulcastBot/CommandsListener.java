package simulcastBot;

import java.util.Collection;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class CommandsListener implements MessageCreateListener {

	private WizardListener wizardListener;

	public CommandsListener(WizardListener wizardListener) {
		this.wizardListener = wizardListener;
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
		// System.out.println("Printing all roles for this user");
		for (Role currRole : userRoles)
		{
			if (currRole.getName().equals("Simulcast"))
			{
				if (message.getContent().equalsIgnoreCase("++room"))
				{

					outputAnnouncement("Room is Open", "https://imgur.com/oFDj1cn.png",
							"Link to room: https://www.rabb.it/seal308");
					// display.toDisplayAnnouncement("Room is Open",
					// "https://imgur.com/oFDj1cn.png",
					// "Link to room: https://www.rabb.it/seal308");
				}

				if (message.getContent().equalsIgnoreCase("++channel"))
				{
					String replyString = "Please enter the number of the channel you want Announcements to be posted.\n";
					replyString += "Or type exit to get out of this wizard\n\n";
					System.out.println(replyString);
					// message.reply("Please select the channel you want Announcements to be
					// posted");

					int counter = 1;
					Collection<Channel> channels = message.getChannelReceiver().getServer().getChannels();

					for (Channel currChannel : channels)
					{
						// message.reply("#" + counter + ": " + currChannel.getName());
						replyString += counter + ": " + currChannel.getName() + "\n";
						counter++;
					}
					message.reply(replyString);

					wizardListener.setUser(message.getAuthor());
					wizardListener.turnOn();

					// WizardListener wizListener = new WizardListener();
					// api.registerListener(wizListener);
				}
			}
		}

	}

	public void outputAnnouncement(String title, String image, String description) {
		Display display = new Display(wizardListener);
		display.outputDisplay(title, image, description);
	}

}
