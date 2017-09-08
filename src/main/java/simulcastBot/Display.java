package simulcastBot;

import java.awt.Color;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;

public class Display {

	private WizardListener wizListener;

	public Display(WizardListener wizListener) {
		this.wizListener = wizListener;
	}

	public void outputDisplay(String title, String image, String description) {
		Color magenta = new Color(255, 0, 255);
		EmbedBuilder eBuilder = new EmbedBuilder().setTitle(title).setImage(image).setDescription(description)
				.setColor(magenta);

		for (Channel currChannel : wizListener.getChannelSet())
		{
			currChannel.sendMessage("", eBuilder);
		}
	}

}
