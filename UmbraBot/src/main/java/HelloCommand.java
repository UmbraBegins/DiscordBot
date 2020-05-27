import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class HelloCommand extends Command {
	
	public HelloCommand() {
		super.name = "hello";
		super.aliases = new String [] {"hello", "hi"};
		super.help = "Simple help command!";
	
	}

	@Override
	protected void execute(CommandEvent event) {
		event.getChannel().sendMessage("Hello!").queue();
	}

}
