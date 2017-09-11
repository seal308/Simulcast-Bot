package simulcastBot;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;

public class ConnectingDiscord {

	private DiscordAPI api;

	// CHANGE
	// private WizardListener wizListener;
	private ChannelWizard channelWizard;
	private CommandsListener commandListener;
	private final String DEFAULT_TEXT_CHANNEL = "353091623464468480";
	private Display display;

	public ConnectingDiscord(String token) {

		api = Javacord.getApi(token, true);

		// CHANGE
		// wizListener = new WizardListener();
		channelWizard = new ChannelWizard();
		commandListener = new CommandsListener(channelWizard);
		display = new Display(channelWizard);

		api.connect(new FutureCallback<DiscordAPI>() {
			public void onSuccess(final DiscordAPI api) {

				// Initial server/channel is "testing2"/"simulcast-task"
				channelWizard.addChannelSet(api.getChannelById(DEFAULT_TEXT_CHANNEL));

				api.registerListener(channelWizard);
				api.registerListener(commandListener);

				botOnlineMessage();

			}

			public void onFailure(Throwable t) {
				// login failed
				t.printStackTrace();
			}
		});
	}

	public void displayAnnouncement(String title, String image, String description) {
		display.outputDisplay(title, image, description);
	}

	/*
	 * Author: Seal 
	 * Description: When bot starts it auto messages a text channel to confirm it works
	 */
	public void botOnlineMessage() {
		display.outputDisplay("Gurren Lagann Ep 1 in Korbin's Room", "https://imgur.com/GULtnXW.png",
				"Link to forums: https://myanimelist.net/forum/?topicid=1655281");
	}

}
