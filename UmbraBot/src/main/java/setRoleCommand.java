import java.awt.Color;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class setRoleCommand extends Command {
	public setRoleCommand() {
		super.name = "setrole";
		super.aliases = new String[] { "setrole", "roleset", "addrole" };
		super.help = "Set the role of a user!";
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Member> members = event.getMessage().getMentionedMembers();
		List<Role> roles = event.getMessage().getMentionedRoles();
		if (members.size() > 1 || roles.size() > 1) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED,
					"You can't have more than one role or member!", "Failed to add role!");
			event.reply(embed.embedBuild());
		} else if (members.size() == 0 || roles.size() == 0) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED, "You need to have 1 role and member!",
					"Failed to add role!");
			event.reply(embed.embedBuild());
		}

		else if (members.get(0).getRoles().contains(roles.get(0))) {
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.RED,
					members.get(0).getAsMention() + " already has that role!", "Failed to add role!");
			event.reply(embed.embedBuild());
		} else {
			event.getGuild().addRoleToMember(members.get(0), roles.get(0)).complete();
			SuccessAndFailEmbed embed = new SuccessAndFailEmbed(Color.GREEN,
					"Successfully added role to" + members.get(0).getAsMention(), "Successfully added role!");
			event.reply(embed.embedBuild());
		}
	}

}
