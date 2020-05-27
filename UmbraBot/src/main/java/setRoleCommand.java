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
		// EVENTUALLY TO MAKE EMEBEDS IN THE FEEDBACK MSG's
		List<Member> members = event.getMessage().getMentionedMembers();
		List<Role> roles = event.getMessage().getMentionedRoles();
		if (members.size() > 1 || roles.size() > 1) {
			event.reply("You can't have more than one role or member!");
		} else if (members.size() == 0 || roles.size() == 0) {
			event.reply("You need to provide a role and/or a member!");
		}

		else if (members.get(0).getRoles().contains(roles.get(0))) {
			event.reply(members.get(0).getAsMention() + " already has that role!");
		} else {
			event.getGuild().addRoleToMember(members.get(0), roles.get(0)).complete();
			event.reply("Successfully added role to " + members.get(0).getAsMention());
		}

	}

}
