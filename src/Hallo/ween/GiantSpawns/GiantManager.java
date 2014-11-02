package Hallo.ween.GiantSpawns;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Hallo.ween.Core.Core;

public class GiantManager implements Listener {


	public static void startup()
	{
		Core.plugin.getServer().getPluginManager().registerEvents(new GiantManager(), Core.plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onSpawn(EntitySpawnEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
			LivingEntity eee = (LivingEntity) event.getEntity();
			if(eee.getType() == EntityType.ZOMBIE)
			{
				Random r = new Random();
				Integer d = r.nextInt(10);
				FileConfiguration c = Core.plugin.getConfig();
				if(d < c.getInt("Giant Spawn Rate") || d == c.getInt("Giant Spawn Rate"))
				{
					LivingEntity e = (LivingEntity) event.getEntity().getLocation().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GIANT);
					event.getEntity().remove();
					ItemStack helmet = new ItemStack(Material.PUMPKIN, 1);
					ItemMeta pm = helmet.getItemMeta();
					pm.setDisplayName("§4Halloween Pumpkin");
					helmet.setItemMeta(pm);			
				    EntityEquipment ee = e.getEquipment();
				    ee.setHelmet(helmet);
				}
			}
		}
	}
}
