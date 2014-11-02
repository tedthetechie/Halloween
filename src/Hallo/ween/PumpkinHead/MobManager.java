package Hallo.ween.PumpkinHead;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Hallo.ween.Core.Core;

public class MobManager implements Listener {

	
	public static void startup()
	{
		Core.plugin.getServer().getPluginManager().registerEvents(new MobManager(), Core.plugin);
	}
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
				ItemStack helmet = new ItemStack(Material.PUMPKIN, 1);
				ItemMeta pm = helmet.getItemMeta();
				pm.setDisplayName("§4Halloween Pumpkin");
				helmet.setItemMeta(pm);			
				LivingEntity e = (LivingEntity) event.getEntity();
			    EntityEquipment ee = e.getEquipment();
			    ee.setHelmet(helmet);
			    }
	}
}
