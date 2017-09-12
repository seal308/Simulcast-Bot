package simulcastBot.discord;

import java.awt.Color;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;

/*
 * Description: Displays announcements
 * Author: Seal
 */

public class Display {

	// private WizardListener wizListener;
	private ChannelWizard channelWizard;

	public Display(ChannelWizard channelWizard) {
		this.channelWizard = channelWizard;
	}

	public void outputDisplay(String title, String image, String description) {
		Color magenta = new Color(255, 0, 255);
		EmbedBuilder eBuilder = new EmbedBuilder().setTitle(title).setImage(image).setDescription(description)
				.setColor(magenta);

		for (Channel currChannel : channelWizard.getChannelSet())
		{
			currChannel.sendMessage("", eBuilder);
		}
	}

}
