package simulcastBot;

import java.awt.Color;
import java.util.Collection;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ConnectingDiscord {

	private DiscordAPI api;

	private WizardListener wizListener;
	// private Display display;

	public ConnectingDiscord(String token) {

		api = Javacord.getApi(token, true);

		wizListener = new WizardListener();
		// display = new Display(wizListener);
		// below was in the displayAnnouncementMethod

		api.connect(new FutureCallback<DiscordAPI>() {
			public void onSuccess(final DiscordAPI api) {

				wizListener.addChannelSet(api.getChannelById("353091623464468480"));

				// Ping Pong! Do what you want now.
				api.registerListener(wizListener);

				api.registerListener(new MessageCreateListener() {
					public void onMessageCreate(DiscordAPI api, Message message) {
						// check the content of the message
						if (message.getContent().equalsIgnoreCase("ping"))
						{
							// reply to the message
							message.reply("pong");
						}

						String currID = "";

						Collection<Role> userRoles = (message.getAuthor()
								.getRoles(message.getChannelReceiver().getServer()));
						// System.out.println("Printing all roles for this user");
						for (Role currRole : userRoles)
						{
							if (currRole.getName().equals("Simulcast"))
							{
								if (message.getContent().equalsIgnoreCase("++room"))
								{

									displayAnnouncement("Room is Open", "https://imgur.com/oFDj1cn.png",
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
									Collection<Channel> channels = message.getChannelReceiver().getServer()
											.getChannels();

									for (Channel currChannel : channels)
									{
										// message.reply("#" + counter + ": " + currChannel.getName());
										replyString += counter + ": " + currChannel.getName() + "\n";
										counter++;
									}
									message.reply(replyString);

									wizListener.setUser(message.getAuthor());
									wizListener.turnOn();

									// WizardListener wizListener = new WizardListener();
									// api.registerListener(wizListener);
								}
							}
						}

					}
				});
				// END PING PONG

				// practiceMessage(api);
				practiceMessage();

			}

			public void onFailure(Throwable t) {
				// login failed
				t.printStackTrace();
			}
		});
	}

	public void displayAnnouncement(String title, String image, String description) {
		Display display = new Display(wizListener);
		display.outputDisplay(title, image, description);
	}

	public void practiceMessage() {
		MessageBuilder builder = new MessageBuilder();
		builder.append("In Practice Message");
		// builder.build();

		Message msg = new PracticeMsgClass();

		msg.edit(builder.build());

		/*
		 * Message msg = new Message(); Channel channelOne =
		 * message.getChannelReceiver();
		 */

		Channel channelOne = api.getChannelById("353091623464468480");
		// channelOne.sendMessage("SDFSDFJSJDFKSJD:FLKSDF:");
		// i dunno how to send a msg to the channel, i can only do strings like above
		// for now.
		// msg = channelOne.sendMessage();

		Color magenta = new Color(255, 0, 255);

		EmbedBuilder eBuilder = new EmbedBuilder();
		eBuilder.setTitle("Gurren Lagann Ep 1 in Korbin's Room");
		eBuilder.setImage("https://imgur.com/GULtnXW.png");
		eBuilder.setDescription("Link to forums: https://myanimelist.net/forum/?topicid=1655281");
		eBuilder.setColor(magenta);
		// eBuilder.setFooter("eg footer","https://i.imgur.com/hS4QtfP.png");
		// eBuilder.setAuthor("Anime Simulcast",
		// "https://myanimelist.net/clubs.php?cid=73532&time=1504337677",
		// "https://i.imgur.com/hS4QtfP.png");

		// eBuilder.setThumbnail("https://i.imgur.com/hS4QtfP.png");
		eBuilder.setUrl("http://www.google.com");
		channelOne.sendMessage("", eBuilder);

	}

}
