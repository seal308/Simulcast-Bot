package simulcastBot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class WizardListener implements MessageCreateListener {

	private Set<Channel> channelSet = new HashSet<Channel>();;

	Boolean on = false;
	User wizUser;
	Set<String> inWizardIDs = new HashSet<String>();

	String wizardInput = "";

	@Override
	public void onMessageCreate(DiscordAPI api, Message messageWiz) {

		if (on == true && inWizardIDs.contains(messageWiz.getAuthor().getId()))
		{

			Collection<Channel> wizChannels = messageWiz.getChannelReceiver().getServer().getChannels();
			ArrayList<Channel> wizAL = new ArrayList<Channel>();
			wizAL.addAll(wizChannels);

			// TODO Auto-generated method stub
			wizardInput = messageWiz.getContent();
			System.out.println("In Wiz: " + wizardInput);
			int index = -1;

			if (wizardInput.equals("exit"))
			{
				turnOff(messageWiz.getAuthor().getId());
			} else
			{

				try
				{
					index = Integer.parseInt(wizardInput);

					if (index > 0 && index <= wizChannels.size())
					{
						messageWiz.reply("Valid channel");
						addChannelSet(wizAL.get(index - 1));
						turnOff(messageWiz.getAuthor().getId());

					} else
					{
						messageWiz.reply("Invalid Response");
					}

				} catch (NumberFormatException nfe)
				{
					messageWiz.reply("Invalid Response");
				}
			}
		}
	}

	public void setUser(User wizKid) {
		wizUser = wizKid;
		inWizardIDs.add(wizKid.getId());
	}

	public void turnOn() {
		on = true;
	}

	public void turnOff(String userRemoveID) {

		if (inWizardIDs.isEmpty())
		{
			on = false;
		}
		// Question, shouldn't the below be above the if statement?
		inWizardIDs.remove(userRemoveID);
	}

	public void addChannelSet(Channel newChannel) {
		System.out.println("ADDCHANNEL_ADDCHANNEL_ADDCHANNEL");
		channelSet.add(newChannel);

	}

	public void removeChannelSet(Channel toDeleteChannel) {
		channelSet.remove(toDeleteChannel);
	}

	public Set<Channel> getChannelSet() {
		return channelSet;
	}

}
