package wffirilat.betterghasts.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItemBase extends Item {

	public ModItemBase(String name) {

		// all Items should include these 4 lines
		this.setUnlocalizedName(Constants.MODID + "_" + name);
		this.setTextureName(Constants.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
}
