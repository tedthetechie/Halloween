package Hallo.ween.BetterWitches;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.util.Vector;

import Hallo.ween.Core.Core;
import Hallo.ween.Utils.ParticleEffect;

public class WitchManager implements Listener {
	
	public static List<LivingEntity> witches = new ArrayList<LivingEntity>();
	public static List<LivingEntity> wfd = new ArrayList<LivingEntity>();
	
	public static void startup()
	{
		Core.plugin.getServer().getPluginManager().registerEvents(new WitchManager(), Core.plugin);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.plugin, new Runnable()
		{
			@SuppressWarnings("deprecation")
			public void run()
			{
				for(LivingEntity e : witches)
				{
					for(Player p : Bukkit.getOnlinePlayers())
					{
						ParticleEffect.WITCH_MAGIC.sendToPlayer(p, e.getLocation().add(0, 2.5, 0), 
								0.1F, 
								0.1F, 
								0.1F, 
								0.01F, 
								3);
					}
				}
			}
		}, 3L, 3L);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
			LivingEntity e = (LivingEntity) event.getEntity();
			Random r = new Random();
			Integer n = r.nextInt(15);
			if(5 > n && witches.contains(e))
			{
				wfd.add(e);
				Vector v = new Vector(0, 1, 0);
				e.setVelocity(v);
			}
			if(witches.contains((LivingEntity) event.getEntity()))
			{
				for(Entity en : e.getNearbyEntities(10, 10, 10))
				{
					if(en instanceof LivingEntity)
					{
						((LivingEntity) en).damage(0.5F);
						for(Player p : Bukkit.getOnlinePlayers())
						{
							ParticleEffect.SNOWBALL_POOF.sendToPlayer(p, e.getLocation(), 0.5F, 1.0F, 0.5F, 0.1F, 50);
						}
					}
				}

			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event)
	{
		if(witches.contains((LivingEntity) event.getEntity()))
		{
			witches.remove((LivingEntity) event.getEntity());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFall(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof LivingEntity && event.getCause() == DamageCause.FALL)
		{
			LivingEntity e = (LivingEntity) event.getEntity();
			if(e.getType() == EntityType.WITCH && witches.contains(e))
			{
				wfd.remove(e);
				for(Entity en : e.getNearbyEntities(10, 10, 10))
				{
					if(en instanceof LivingEntity)
					{
						((LivingEntity) en).damage(2);
						for(Player p : Bukkit.getOnlinePlayers())
						{
							ParticleEffect.SNOWBALL_POOF.sendToPlayer(p, e.getLocation(), 0.5F, 1.0F, 0.5F, 0.1F, 50);
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTarget(EntityTargetEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
				LivingEntity e = (LivingEntity) event.getEntity();
				if(e.getType() == EntityType.WITCH && witches.contains(e) && event.getTarget() instanceof Player)
				{
					for(Player p : Bukkit.getOnlinePlayers())
					{
						ParticleEffect.FLAME.sendToPlayer(p, e.getLocation(), 0.5F, 1.0F, 0.5F, 0.03F, 40);
					}
				}	
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSpawn(EntitySpawnEvent event)
	{
		if(event.getEntity() instanceof LivingEntity)
		{
			LivingEntity e = (LivingEntity) event.getEntity();
			Random r = new Random();
			Integer n = r.nextInt(10);
			if(n < Core.plugin.getConfig().getInt("Better Witches Spawn Rate") && e.getType() == EntityType.WITCH)
			{
				witches.add(e);
			}
		}
	}
}
