package wffirilat.betterghasts.blocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.creativetab.CreativeTabs;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockAsh extends BlockFalling {

	public String name = "ash";

	public BlockAsh() {
		super();
		this.setBlockName(Constants.MODID + "_" + this.name);
		this.setBlockTextureName(Constants.MODID + ":" + this.name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, this.name);
	}

}
