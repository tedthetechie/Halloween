package Hallo.ween.PermanentNight;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import Hallo.ween.Core.Core;

public class TimeManager {

	
	public static void startup()
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.plugin, new Runnable()
		{
			public void run()
			{
				FileConfiguration config = Core.plugin.getConfig();
				for(String string : config.getStringList("Permanent Night Worlds"))
				{
					if(Bukkit.getWorld(string) != null)
					{
						Bukkit.getWorld(string).setTime(18001);
						Bukkit.getWorld(string).setTime(18000);
					}
				}
			}
		}, 5L, 5L);
	}
}
