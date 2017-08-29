package simulcastBot;

import de.btobastian.javacord.*;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import com.google.common.util.concurrent.FutureCallback;
public class ConnectingDiscord {
	
	 public ConnectingDiscord(String token) {
	        //DiscordAPI api = Javacord.getApi(email, password);
		 DiscordAPI api = Javacord.getApi(token, true);
		 api.connect(new FutureCallback<DiscordAPI>() {
			  public void onSuccess(final DiscordAPI api) {
			    // do what you want now
				  
				  //Ping Pong Code!
				// register listener
	                api.registerListener(new MessageCreateListener() {
	                    public void onMessageCreate(DiscordAPI api, Message message) {
	                        // check the content of the message
	                        if (message.getContent().equalsIgnoreCase("ping")) {
	                            // reply to the message
	                            message.reply("pong");
	                        }
	                    }
	                });
	                // END PING PONG
				  
			  }

			  public void onFailure(Throwable t) {
			    // login failed
			    t.printStackTrace();
			  }
			});
	    }

}
