package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import GiphyHandling.GiphyHand;
import net.dv8tion.jda.api.EmbedBuilder;

public class HelloCommand extends Command {

	public HelloCommand() {
		super.name = "hello";
		super.aliases = new String[] { "hello", "hi" };
		super.help = "Simple help command!";

	}

	@Override
	protected void execute(CommandEvent event) {
		GiphyHand gifs = new GiphyHand();
		EmbedBuilder embed = new EmbedBuilder();
		embed.setImage(gifs.getURL("sex"));
		event.reply(embed.build());
	}
}
