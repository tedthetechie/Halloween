package Hallo.ween.Core;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Hallo.ween.BetterWitches.WitchManager;
import Hallo.ween.Commands.HalloweenCmd;
import Hallo.ween.Data.Options;
import Hallo.ween.DeathZombie.DeathManager;
import Hallo.ween.GiantSpawns.GiantManager;
import Hallo.ween.PermanentNight.TimeManager;
import Hallo.ween.PumpkinHead.MobManager;
import Hallo.ween.RandomScare.ScareManager;

public class Core extends JavaPlugin {
	
	public static String p = HalloweenCmd.p;
	public static Plugin plugin = null;
	
	public void onEnable()
	{
		plugin = this;
		saveDefaultConfig();
		FileConfiguration config = getConfig();
		Options.CREATURESWEARPUMPKINS = config.getBoolean("Creatures Wear Pumpkins");
		Options.PERMANENTNIGHT = config.getBoolean("Permanent Night");
		Options.RANDOMSCARE = config.getBoolean("Random Screen Scare");
		Options.DEATHZOMBIE = config.getBoolean("Zombie On Death");
		Options.GIANTSPAWN = config.getBoolean("Giant Spawn Chance");
		Options.BETTERWITCHES = config.getBoolean("Better Witches");
		if(Options.PERMANENTNIGHT)
			TimeManager.startup();
		if(Options.CREATURESWEARPUMPKINS)
			MobManager.startup();
		if(Options.DEATHZOMBIE)
			DeathManager.startup();
		if(Options.RANDOMSCARE)
			ScareManager.startup();
		if(Options.GIANTSPAWN)
			GiantManager.startup();
		if(Options.BETTERWITCHES)
			WitchManager.startup();
		getCommand("halloween").setExecutor(new HalloweenCmd());
	
		if(Bukkit.getWorld("Halloween") == null)
		{
			Bukkit.getConsoleSender().sendMessage(HalloweenCmd.p + "§a§lGENERATING HALLOWEEN WORLD");
			Bukkit.getConsoleSender().sendMessage(p + "§a§lGENERATION COMPLETE");
			
		}
	}
	
	public void onDisable()
	{

	}

}
