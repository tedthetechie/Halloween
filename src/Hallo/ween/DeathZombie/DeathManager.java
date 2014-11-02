package Hallo.ween.DeathZombie;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import Hallo.ween.Core.Core;

public class DeathManager implements Listener {

	public static void startup()
	{
		Core.plugin.getServer().getPluginManager().registerEvents(new DeathManager(), Core.plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDeath(PlayerDeathEvent event)
	{
		Player player = event.getEntity();
		Location location = player.getLocation();
		location.getWorld().strikeLightning(location);
		LivingEntity e = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
		EntityEquipment d = e.getEquipment();
		@SuppressWarnings("deprecation")
		ItemStack skull = new ItemStack(397, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(player.getName());
		skull.setItemMeta(meta);
		d.setHelmet(skull);
		d.setHelmetDropChance(50F);
	}
	
}
