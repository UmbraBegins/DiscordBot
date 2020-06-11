import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import Commands.ClearCommand;
import Commands.GifCommand;
import Commands.HelloCommand;
import Commands.WeatherCommand;
import Commands.removeRoleCommand;
import Commands.setRoleCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

	public static void main(String[] args) throws LoginException {
		JDA jda = JDABuilder.createDefault("Njk0MjgzMjA4NzQwODMxMzEy.Xsl-ng.cNYDOyLGI2R0r5OEH-TGhVcDuco").build();
		CommandClientBuilder build = new CommandClientBuilder();
		build.setPrefix("!");
		build.setOwnerId("132252880114548738");
		CommandClient client = build.build();
		client.addCommand(new HelloCommand());
		client.addCommand(new setRoleCommand());
		client.addCommand(new ClearCommand());
		client.addCommand(new removeRoleCommand());
		client.addCommand(new WeatherCommand());
		client.addCommand(new GifCommand());
		jda.addEventListener(client);

	}

}
