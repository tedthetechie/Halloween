package Hallo.ween.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HalloweenCmd implements CommandExecutor{

	public static String p = "§8[§cHalloween§8] §r";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2,
			String[] args) {
		if(cmd.getName().equalsIgnoreCase("halloween"))
		{
			if(args.length == 0)
			{
				sender.sendMessage(p + "§aHalloween! Made by §bTedTheTechie");
				return false;
			}
			if(args[0].equalsIgnoreCase("help"))
			{
				sender.sendMessage(p + "§r---------§c[§aHelp§c]§r---------");
				sender.sendMessage(p + "§a/halloween §b- §eCredits");
				sender.sendMessage(p + "§a/halloween reload §b- §eReload the Configuration");
				sender.sendMessage(p + "§a/halloween help §b- §eShows this help page");
				return false;
			}
			if(args[0].equalsIgnoreCase("reload"))
			{
				if(sender instanceof Player)
				{
					Player player = (Player) sender;
					if(!(player.hasPermission("halloween.reload")))
					{
						player.sendMessage(p + "§cYou do not have permission to preform this command!");
						return false;
					}
					sender.sendMessage(p + "§cComing Soon...");
				} else {
					sender.sendMessage(p + "§cComing Soon...");
				}
			return false;
			}
			sender.sendMessage(p + "§r------§c[§aHelp§c]§r-----");
			sender.sendMessage(p + "§a/halloween §b- §eCredits");
			sender.sendMessage(p + "§a/halloween reload §b- §eReload the Configuration");
			sender.sendMessage(p + "§a/halloween help §b- §eShows this help page");
		}
		return false;
	}
	
}
