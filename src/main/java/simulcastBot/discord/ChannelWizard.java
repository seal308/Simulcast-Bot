package simulcastBot.discord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;

/*
 * Description: Wizard to decide on which channel to receive announcements
 * Author: Seal
 */

public class ChannelWizard extends Wizard {

	private Collection<Channel> wizChannels;
	private ArrayList<Channel> wizAL;

	@Override
	public void onMessageCreate(DiscordAPI api, Message messageWiz) {
		wizChannels = messageWiz.getChannelReceiver().getServer().getChannels();
		wizAL = new ArrayList<Channel>();
		wizAL.addAll(wizChannels);
		runWizard(messageWiz);
	}

	@Override
	public void validResponse(Message messageWiz) {
		messageWiz.reply("Valid Channel");
	}

	@Override
	public void invalidResponse(Message messageWiz) {
		messageWiz.reply("Invalid Channel, try again or type exit to leave wizard");
	}

	@Override
	public void validOperation(Message messageWiz, int index) {
		addChannelSet(wizAL.get(index - 1));
	}

	@Override
	public int getRange() {
		int returnValue = -1;
		returnValue = wizChannels.size();
		return returnValue;
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

	@Override
	public void displayOptions(Message message) {
		String replyString = "Please enter the number of the channel you want Announcements to be posted.\n";
		replyString += "Or type exit to get out of this wizard\n\n";

		int counter = 1;
		Collection<Channel> channels = message.getChannelReceiver().getServer().getChannels();

		for (Channel currChannel : channels)
		{
			replyString += counter + ": " + currChannel.getName() + "\n";
			counter++;
		}
		message.reply(replyString);

	}

}
