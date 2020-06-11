package Commands;

import java.awt.Color;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import GiphyHandling.GiphyHand;
import net.dv8tion.jda.api.EmbedBuilder;

public class GifCommand extends Command {

	public GifCommand() {
		super.aliases = new String[] { "gif" };
		super.name = "gif command!";
		super.help = "Send funny gifs!";
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] msg = event.getMessage().getContentRaw().split(" ");
		if (msg.length == 2) {
			GiphyHand gif = new GiphyHand();
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Here, take a gif about " + msg[1]);
			embed.setColor(Color.ORANGE);
			embed.setImage(gif.getURL(msg[1]));
			event.reply(embed.build());
		}
	}

}
