package wffirilat.betterghasts.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DeadGrass extends Block {

	public static String name = "deadGrass";

	public DeadGrass() {
		super(Material.grass);

		// all Blocks should include these 4 lines
		setBlockName(Constants.MODID + "_" + name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.dirt);
	}

	public IIcon topTexture;
	public IIcon sideTexture;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
		this.topTexture = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.sideTexture = p_149651_1_.registerIcon(this.getTextureName() + "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.topTexture : side == 0 ? this.blockIcon : this.sideTexture;
	}

}
