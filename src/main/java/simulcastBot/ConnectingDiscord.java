package simulcastBot;

import de.btobastian.javacord.*;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.CustomEmoji;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageAttachment;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageReceiver;
import de.btobastian.javacord.entities.message.Reaction;
import de.btobastian.javacord.entities.message.embed.Embed;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import java.awt.Color;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.FutureCallback;
public class ConnectingDiscord {
	
	private DiscordAPI api;
	
	 public ConnectingDiscord(String token) {
	        //DiscordAPI api = Javacord.getApi(email, password);
		 api = Javacord.getApi(token, true);
		 Channel channel1 = api.getChannelById("353091623464468480");
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
	                        
	                        if (message.getContent().equalsIgnoreCase("++room")) {
	  	      				  			  
	  	      				  String currID = "";
	  	      			
	  	      				  Collection<Role> userRoles = (message.getAuthor().getRoles(message.getChannelReceiver().getServer()));
	  	      				  System.out.println("Printing all roles for this user");
	  	      				  for (Role currRole : userRoles)
	  	      				  {
	  	      					  if (currRole.getName().equals("Simulcast"))
	  	      					  {
	  	      						  displayAnnouncement("Room is Open","https://imgur.com/oFDj1cn.png","Link to room: https://www.rabb.it/seal308");
	  	      					  }
	  	      				  }
	                            
	                        }
	                        
	                        
	                  
	                        
	                        /*
	                      Collection<Server> servers = api.getServers();
	      				  Server firstServer = servers.iterator().next();
	      				  */
	                        
	                        /*
	      				  
	      				  
	      				  String currID = "";
	      			
	      				  //Collection<Role> userRoles = (message.getAuthor().getRoles(firstServer));
	      				  // above works, below seems to work:(
	      				  Collection<Role> userRoles = (message.getAuthor().getRoles(message.getChannelReceiver().getServer()));
	      				  System.out.println("Printing all roles for this user");
	      				  for (Role currRole : userRoles)
	      				  {
	      					  System.out.println(currRole.getId());
	      					  currID = currRole.getId();
	      					 //if (currID.equals("353590036769538069"))
	      					  if (currRole.getName().equals("Simulcast"))
	      					  {
	      						  System.out.println("You Are VERIFIED");
	      						  message.reply("You Are VERIFIED");
	      					  }
	      					  else
	      					  {
	      						  System.out.println("SEAL SUCKS!!!!");
	      						  message.reply("ACCESS DENIED!!!");
	      					  }
	      				  }
	      				  
	      				  */ //line 85
	      				  
	                        
	                        /*
	                        if (message.getAuthor().getRoles(ADD_SERVER))
	                        {
	                        	
	                        }
	                        */
	                        
	                        
	                       
	                        
	                    }
	                });
	                // END PING PONG
	               
	                //practiceMessage(api);
	                practiceMessage();
	                
	                
				  
			  }

			  public void onFailure(Throwable t) {
			    // login failed
			    t.printStackTrace();
			  }
			});
	    }
	 
	 public void displayAnnouncement(String title, String image, String description)
	 {
		 Channel channelOne = api.getChannelById("353091623464468480");
		 Color magenta = new Color(255,0,255);
		 EmbedBuilder eBuilder = new EmbedBuilder();
		 eBuilder.setTitle(title);
		 eBuilder.setImage(image);
		 eBuilder.setDescription(description);
		 eBuilder.setColor(magenta);
		 channelOne.sendMessage("", eBuilder);	 
	 }
	 
	 
	 public void practiceMessage()
	 {
		 MessageBuilder builder = new MessageBuilder();
		 builder.append("In Practice Message");
		 //builder.build();
		 
		 Message msg = new PracticeMsgClass();
		
		 
		 msg.edit(builder.build());
		 
		 /*
		 Message msg = new Message();
		 Channel channelOne = message.getChannelReceiver();
		 */
		 
		 
		 Channel channelOne = api.getChannelById("353091623464468480");
		 //channelOne.sendMessage("SDFSDFJSJDFKSJD:FLKSDF:");
		 // i dunno how to send a msg to the channel, i can only do strings like above for now.
		 //msg = channelOne.sendMessage();
		 
		 Color magenta = new Color(255,0,255);
		 
		 EmbedBuilder eBuilder = new EmbedBuilder();
		 eBuilder.setTitle("Gurren Lagann Ep 1 in Korbin's Room");
		 eBuilder.setImage("https://imgur.com/GULtnXW.png");
		 eBuilder.setDescription("Link to forums: https://myanimelist.net/forum/?topicid=1655281");
		 eBuilder.setColor(magenta);
		 //eBuilder.setFooter("eg footer","https://i.imgur.com/hS4QtfP.png");
		 //eBuilder.setAuthor("Anime Simulcast", "https://myanimelist.net/clubs.php?cid=73532&time=1504337677", "https://i.imgur.com/hS4QtfP.png");
		 
		 //eBuilder.setThumbnail("https://i.imgur.com/hS4QtfP.png");
		 eBuilder.setUrl("http://www.google.com");
		 channelOne.sendMessage("", eBuilder);
		 
		 
		 
	 }
	 
	 public class PracticeMsgClass implements Message
	 {

		@Override
		public int compareTo(Message arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Future<Void> addCustomEmojiReaction(CustomEmoji arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Void> addUnicodeReaction(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Void> delete() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Void> edit(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<MessageAttachment> getAttachments() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public User getAuthor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Channel getChannelReceiver() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Calendar getCreationDate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<Embed> getEmbeds() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Role> getMentionedRoles() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<User> getMentions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNonce() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Reaction> getReactions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MessageReceiver getReceiver() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public User getUserReceiver() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isDeleted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isMentioningEveryone() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPinned() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isPrivateMessage() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isTts() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Future<Void> removeAllReactions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Message> reply(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Message> reply(String arg0, EmbedBuilder arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Message> reply(String arg0, FutureCallback<Message> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Future<Message> reply(String arg0, EmbedBuilder arg1, FutureCallback<Message> arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		 
	 }

}
