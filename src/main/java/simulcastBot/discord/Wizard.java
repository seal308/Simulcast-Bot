package simulcastBot.discord;

import java.util.HashSet;
import java.util.Set;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/*
 * Description: 
 * 		Displays numbered options. 
 * 		If user doesn't select valid option wizard checks for their next response.
 * 		If user types exit, they get out of the wizard.
 * 		Extend to make your own specific wizards.
 * Author: Seal
 */

public abstract class Wizard implements MessageCreateListener {

	protected Set<Channel> channelSet = new HashSet<Channel>();;

	private Boolean on = false;
	private User wizUser;
	private Set<String> inWizardIDs = new HashSet<String>();

	private String wizardInput = "";

	public abstract void validResponse(Message messageWiz);

	public abstract void invalidResponse(Message messageWiz);

	public abstract void validOperation(Message messageWiz, int index);

	public abstract void displayOptions(Message messageWiz);

	public abstract int getRange();

	@Override
	public void onMessageCreate(DiscordAPI api, Message messageWiz) {

		runWizard(messageWiz);
	}

	public void runWizard(Message messageWiz) {
		if (on == true && inWizardIDs.contains(messageWiz.getAuthor().getId()))
		{
			wizardInput = messageWiz.getContent();
			int index = -1;

			if (wizardInput.equals("exit"))
			{
				turnOff(messageWiz.getAuthor().getId());
			} else
			{
				try
				{
					index = Integer.parseInt(wizardInput);
					inRange(index, getRange(), messageWiz);

				} catch (NumberFormatException nfe)
				{
					invalidResponse(messageWiz);
				}
			}
		}
	}

	public void inRange(int index, int range, Message messageWiz) {
		if (index > 0 && index <= range)
		{
			validSelection(messageWiz, index);
		} else
		{
			invalidResponse(messageWiz);
		}
	}

	public void validSelection(Message msgWiz, int index) {
		validResponse(msgWiz);
		validOperation(msgWiz, index);
		turnOff(msgWiz.getAuthor().getId());
	}

	public void turnOn(Message msgWiz) {
		displayOptions(msgWiz);
		on = true;
		wizUser = msgWiz.getAuthor();
		inWizardIDs.add(wizUser.getId());
	}

	public void turnOff(String userRemoveID) {

		inWizardIDs.remove(userRemoveID);

		if (inWizardIDs.isEmpty())
		{
			on = false;
		}

	}

}
