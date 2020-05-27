import java.awt.Color;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Message;

public class ClearCommand extends Command {
	public ClearCommand() {
		super.name = "Clear";
		super.aliases = new String[] { "clear", "purge" };
		super.help = "Clear past messages!";
	}

	@Override
	protected void execute(CommandEvent event) {
		int number = 0;
		String[] msgSent = event.getMessage().getContentRaw().split(" ");
		if (msgSent.length > 2 || msgSent.length == 1) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "Invalid format, try !clear (number)",
					"Failed to clear!");
			event.reply(embed.embedBuild());
			return;
		} else {
			try {
				number = Integer.parseInt(msgSent[1]);
			} catch (NumberFormatException e) {
				SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "Did not enter a valid number!",
						"Failed to clear!");
				event.reply(embed.embedBuild());
				return;
			}
		}
		if (number == 0 || number > 100) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "Enter a nmumber from 1-100 (Discord limit)",
					"Failed to clear!");
			event.reply(embed.embedBuild());
		} else {
			try {
				List<Message> msg = event.getChannel().getHistory().retrievePast(number).complete();
				event.getChannel().purgeMessages(msg);
				SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.GREEN, "Successfully purged!", "Success!");
				event.reply(embed.embedBuild());
			} catch (IllegalArgumentException e) {
				SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "Messages are too old!",
						"Failed to clear!");
				event.reply(embed.embedBuild());
			}
		}
	}
}
