package wffirilat.betterghasts.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PoisonJet extends Block {

	public String name = "poisonJet";
	public IIcon topTexture;
	public IIcon sideTexture;

	public PoisonJet() {
		super(Material.grass);

		this.setBlockName(Constants.MODID + "_" + this.name);
		this.setBlockTextureName(Constants.MODID + ":" + this.name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, this.name);

		this.needsRandomTick = true;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int dx;
		int ypos;
		int dz;
		for (int i = 0; i < 5; i++) {
			dx = rand.nextInt(10) - 5;
			dz = rand.nextInt(10) - 5;
			ypos = world.getHeightValue(x + dx, z + dz) + rand.nextInt(3);
			if (world.getBlock(x + dx, ypos, z + dx) == Blocks.air) {
				world.setBlock(x + dx, ypos, z + dz, ModBlocks.poisonAir);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon(Constants.MODID + ":" + "deadGrass" + "_bottom");
		this.topTexture = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.sideTexture = p_149651_1_.registerIcon(Constants.MODID + ":" + "deadGrass" + "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.topTexture : side == 0 ? this.blockIcon : this.sideTexture;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.dirt);
	}
}