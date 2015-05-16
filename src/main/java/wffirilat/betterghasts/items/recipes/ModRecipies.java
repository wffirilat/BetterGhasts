package wffirilat.betterghasts.items.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipies {

	public static void init() {

		GameRegistry.addRecipe(new ItemStack(ModItems.EssencePoison), " S ", " S ", "SSS", 'S', ModItems.poisonGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModItems.EssenceBlind), " S ", " S ", "SSS", 'S', ModItems.blindGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModItems.EssenceVoid), " S ", " S ", "SSS", 'S', ModItems.voidGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModItems.EssenceFlame), " S ", " S ", "SSS", 'S', ModItems.flameGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModItems.EssenceCreepy), " S ", " S ", "SSS", 'S', ModItems.creepyGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModItems.EssenceBlaze), " S ", " S ", "SSS", 'S', ModItems.blazeGhastTear);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ghastRage), "YSY", "SYS", "YSY", 'S', ModItems.EssenceCreepy, 'Y', Items.gunpowder);

		//
		// FORMAT GameRegistry.addRecipe(new ItemStack(<result>), "SSS", "SCS",
		// "SSS", 'S', <item for S>, 'C', <item for C>);
		//
		// result:
		// _____________________________________
		// __________+---+---+---+______________
		// __________| S | S | S |______________
		// __________+---+---+---+_______+---+__
		// __________| S | C | S |___==>_| R |__
		// __________+---+---+---+_______+---+__
		// __________| S | S | S |______________
		// __________+---+---+---+______________
		// _____________________________________

	}

}
