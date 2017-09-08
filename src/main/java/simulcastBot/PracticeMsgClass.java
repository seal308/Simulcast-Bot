package simulcastBot;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.CustomEmoji;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageAttachment;
import de.btobastian.javacord.entities.message.MessageReceiver;
import de.btobastian.javacord.entities.message.Reaction;
import de.btobastian.javacord.entities.message.embed.Embed;
import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import de.btobastian.javacord.entities.permissions.Role;

public class PracticeMsgClass implements Message {

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
