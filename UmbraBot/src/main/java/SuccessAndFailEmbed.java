import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class SuccessAndFailEmbed {
	private Color color;
	private String desc;
	private String title;

	public SuccessAndFailEmbed(Color color, String desc, String title) {
		this.color = color;
		this.desc = desc;
		this.title = title;
	}

	public MessageEmbed embedBuild() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(title);
		embed.setDescription(desc);
		embed.setColor(color);
		return embed.build();
	}

}
