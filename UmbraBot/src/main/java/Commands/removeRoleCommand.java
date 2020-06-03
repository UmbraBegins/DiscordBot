package Commands;

import java.awt.Color;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import Embeds.SuccessAndFailEmbed;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class removeRoleCommand extends Command {
	public removeRoleCommand() {
		super.name = "removerole";
		super.aliases = new String[] { "removerole", "roleremove", "deleterole" };
		super.help = "Remove a role of a user!";
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Member> members = event.getMessage().getMentionedMembers();
		List<Role> roles = event.getMessage().getMentionedRoles();
		if (members.size() > 1 || roles.size() > 1) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED,
					"You can't have more than one role or member!", "Failed to remove role! :cry:");
			event.reply(embed.embedBuild());
		} else if (members.size() == 0 || roles.size() == 0) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "You need to have 1 role and member!",
					"Failed to remove role! :cry:");
			event.reply(embed.embedBuild());
		} else if (!(members.get(0).getRoles().contains(roles.get(0)))) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED,
					members.get(0).getAsMention() + " doesn't have that role!", "Failed to remove role! :cry:");
			event.reply(embed.embedBuild());
		} else {
			event.getGuild().removeRoleFromMember(members.get(0), roles.get(0)).complete();
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.GREEN,
					"Successfully removed role to" + members.get(0).getAsMention(),
					"Successfully removed role! :smile:");
			event.reply(embed.embedBuild());
		}
	}

}
