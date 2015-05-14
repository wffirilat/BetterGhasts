package wffirilat.betterghasts.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlockBase extends Block {

	public ModBlockBase(String name) {
		this(Material.rock, name);

	}

	public ModBlockBase(Material mat, String name) {
		super(mat);

		// all Blocks should include these 4 lines
		this.setBlockName(Constants.MODID + "_" + name);
		this.setBlockTextureName(Constants.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
	}

}
