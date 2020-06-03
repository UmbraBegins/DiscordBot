package Commands;

import java.awt.Color;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Embeds.SuccessAndFailEmbed;
import WeatherHandling.WeatherHand;
import net.dv8tion.jda.api.EmbedBuilder;

public class WeatherCommand extends Command {
	public WeatherCommand() {
		super.name = "Weather";
		super.aliases = new String[] { "weather", "weatherin" };
		super.help = "Check the weather in your area!";
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] msg = event.getMessage().getContentRaw().split(" ");
		if (msg.length == 1 || msg.length > 2) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED,
					"Too many argumnets! Try !weather {city/zip}", "Failed Check Weather!");
			return;
		}
		WeatherHand.loadWebsite(msg[1]);
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Weather report for " + WeatherHand.getLocName() + ", " + WeatherHand.getCountry());
		embed.setThumbnail("https://openweathermap.org/img/w/" + WeatherHand.getIcon() + ".png");
		embed.setColor(Color.CYAN);
		embed.addField("Country", ":flag_" + WeatherHand.getCountry().toLowerCase() + ":", true);
		embed.addField("Temperature", String.format("%.2f", WeatherHand.getCurrTempFar()) + "°F " + "| "
				+ String.format("%.2f", WeatherHand.getCurrTempCel()) + "°C", true);
		embed.addField("Humidity", WeatherHand.getHumi() + "%", true);
		embed.setFooter("Request was made by " + event.getMember().getUser().getAsTag(),
				event.getMember().getUser().getAvatarUrl());
		event.reply(embed.build());
	}

}
