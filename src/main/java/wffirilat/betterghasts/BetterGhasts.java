package wffirilat.betterghasts;

import net.minecraft.world.WorldType;

import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.dimensions.ModDimensions;
import wffirilat.betterghasts.events.ModEvents;
import wffirilat.betterghasts.events.TerrainEvents;
import wffirilat.betterghasts.gen.ModOreGenerators;
import wffirilat.betterghasts.gen.biome.ModBiomes;
import wffirilat.betterghasts.gen.biome.WorldTypeBetterGhasts;
import wffirilat.betterghasts.items.ModItems;
import wffirilat.betterghasts.lib.Constants;
import wffirilat.betterghasts.gen.ModOreGenerators;
import wffirilat.betterghasts.items.enchantments.ModEnchantments;
import wffirilat.betterghasts.items.recipes.ModRecipies;
import wffirilat.betterghasts.lib.Constants;
import wffirilat.betterghasts.mobs.ModMobs;
import wffirilat.betterghasts.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

//TODO Localize- UnderdarkPortal
//TODO Localize- GhastRage
//TODO Localize- Shadow
//TODO Localize- Shade
//TODO Localize- PoisonGhast
//TODO Localize- BlindGhast
//TODO Localize- CreepyGhast
//TODO Localize- VoidGhast
//TODO Localize- BlazeGhast
//TODO Localize- EssenceCreepy
//TODO Localize- EssenceFlame
//TODO Localize- EssenceVoid
//TODO Localize- EssenceBlind
//TODO Localize- EssenceBlaze
//TODO Localize- EssencePoison
//TODO Localize- CreepyGhastTear
//TODO Localize- VoidGhastTear
//TODO Localize- BlindGhastTear
//TODO Localize- BlazeGhastTear
//TODO Localize- PoisonGhastTear
//TODO Localize- PoisonFireball
//TODO Localize- SlowEnchant
//TODO Texture- GhastRage
//TODO Texture- Shade
//TODO Texture- EssenceCreepy
//TODO Texture- EssenceFlame
//TODO Texture- EssenceVoid
//TODO Texture- EssenceBlind
//TODO Texture- EssenceBlaze
//TODO Texture- EssencePoison
//TODO Texture- BlindGhastTear
//TODO Texture- PoisonGhastTear
//TODO Texture- VoidGhastTear
//TODO Texture- BlazeGhastTear
//TODO Texture- CreepyGhastTear
//TODO Texture- BlindGhast
//TODO Texture- VoidGhast
//TODO Texture- BlazeGhast
//TODO Texture- CreepyGhast
//TODO Texture- BlindGhast egg
//TODO Texture- VoidGhast egg
//TODO Texture- BlazeGhast egg
//TODO Texture- CreepyGhast egg
//TODO Texture- PoisonGhast egg

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION)
public class BetterGhasts {

	@Instance(Constants.MODID)
	public static BetterGhasts modInstance;

	public static final int dimensionId = 8;

	public static TerrainEvents terrainEvents = new TerrainEvents();

	@SidedProxy(clientSide = "wffirilat.betterghasts.proxy.ClientProxy", serverSide = "wffirilat.betterghasts.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler()
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
		ModRecipies.init();
		ModOreGenerators.init();
		ModMobs.init();
		ModBiomes.init();
		ModEvents.init();
		ModDimensions.init();
		ModEnchantments.init();

		proxy.registerRenderers();

	}

	@EventHandler()
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler()
	public void postInit(FMLPostInitializationEvent event) {
		WorldType GHASTS = new WorldTypeBetterGhasts("Better Ghasts");
	}
}
