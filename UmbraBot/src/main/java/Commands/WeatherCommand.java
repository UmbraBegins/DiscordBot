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
			event.reply(embed.embedBuild());
			return;
		}
		WeatherHand weather = new WeatherHand();
		if (weather.loadWebsite(msg[1]).equalsIgnoreCase("Failed")) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "Invalid location! Try !weather {city/zip}",
					"Failed Check Weather!");
			event.reply(embed.embedBuild());
			return;
		}
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Weather report for " + weather.getLocName() + ", " + weather.getCountry());
		embed.setThumbnail("https://openweathermap.org/img/w/" + weather.getIcon() + ".png");
		embed.setColor(Color.CYAN);
		embed.addField("Country", ":flag_" + weather.getCountry().toLowerCase() + ":", true);
		embed.addField("Temperature", String.format("%.2f", weather.getCurrTempFar()) + "°F " + "| "
				+ String.format("%.2f", weather.getCurrTempCel()) + "°C", true);
		embed.addField("Humidity", weather.getHumi() + "%", true);
		embed.setFooter("Request was made by " + event.getMember().getUser().getAsTag(),
				event.getMember().getUser().getAvatarUrl());
		event.reply(embed.build());
	}

}
