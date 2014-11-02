package Hallo.ween.RandomScare;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Hallo.ween.Core.Core;

public class ScareManager implements Listener {

	public static int random = 1;
	public static void startup()
	{
		Core.plugin.getServer().getPluginManager().registerEvents(new ScareManager(), Core.plugin);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.plugin, new Runnable()
		{
			@SuppressWarnings("deprecation")
			public void run()
			{
				Random r = new Random();
				Integer n = r.nextInt(6);
				if(n > 0 && 3 > n)
				{
					for(final Player p : Bukkit.getOnlinePlayers())
					{
						final Map<Player, ItemStack> helmet = new HashMap<Player, ItemStack>();
						if(p.getInventory().getHelmet() != null)
						helmet.put(p, p.getInventory().getHelmet());
						ItemStack pa = new ItemStack(Material.PUMPKIN, 1);
						ItemMeta pm = pa.getItemMeta();
						pm.setDisplayName("§3Scary Mask!");
						pa.setItemMeta(pm);
						p.getInventory().setHelmet(pa);
						if(helmet.containsKey(p))
						p.getInventory().addItem(helmet.get(p));
						if(helmet.containsKey(p))
						helmet.remove(p);
					}
				}
			}
		}, 720L, 720L);
	}
	
}
