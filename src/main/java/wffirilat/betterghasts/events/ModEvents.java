package wffirilat.betterghasts.events;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import wffirilat.betterghasts.items.ModItems;
import wffirilat.betterghasts.items.enchantments.ModEnchantments;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEvents {

	private static TerrainEvents terrainEvents = new TerrainEvents();
	private static ModEvents events = new ModEvents();
	private static OreGenEvents oreGenEvents = new OreGenEvents();

	public static void init() {
		FMLCommonHandler.instance().bus().register(terrainEvents);

		MinecraftForge.EVENT_BUS.register(events);
		MinecraftForge.TERRAIN_GEN_BUS.register(terrainEvents);
		MinecraftForge.ORE_GEN_BUS.register(oreGenEvents);
	}

	/*
	 * FORMAT
	 * 
	 * @SubscribeEvent public void whatEver(<EventType> event) { doSomething; }
	 */

	@SubscribeEvent
	public void ghastTears(AnvilUpdateEvent event) {
		// is it possible to make it free? cost = 0 doesn't work
		if (event.right.getItem() == ModItems.EssenceFlame) {
			ItemStack i = event.left.copy();
			i.addEnchantment(Enchantment.fireAspect, 5);
			event.output = i;
			event.cost = 1;
		}
		else if (event.right.getItem() == ModItems.EssenceBlaze) {
			ItemStack i = event.left.copy();
			i.addEnchantment(Enchantment.fireAspect, 2);
			i.addEnchantment(Enchantment.smite, 2);
			event.output = i;
		} else if (event.right.getItem() == ModItems.EssenceVoid) {
			ItemStack i = event.left.copy();
			i.addEnchantment(Enchantment.sharpness, 6);
			event.output = i;
			event.cost = 1;
		} else if (event.right.getItem() == ModItems.EssenceCreepy) {
			ItemStack i = event.left.copy();
			i.addEnchantment(Enchantment.knockback, 5);
			event.output = i;
			event.cost = 1;
		}

		// TODO 2- more ghast tear enchantment
		// TODO Poison, blind,

	}

	@SubscribeEvent
	public void handlePotionEnchant(AttackEntityEvent event) {
		System.out.println("recieved event");
		ItemStack item = event.entityPlayer.getItemInUse();
		if (item != null) {
			int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.slowEnchant.effectId, item);
			if (level != 0 && event.target instanceof EntityLivingBase) {
				System.out.println("Slowing down");
				((EntityLivingBase) event.target).addPotionEffect(new PotionEffect(ModEnchantments.slowEnchant.potionId, 50, level - 1));

			}
		}
	}
}
