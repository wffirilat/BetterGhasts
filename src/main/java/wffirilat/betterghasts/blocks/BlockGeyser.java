package wffirilat.betterghasts.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGeyser extends Block {
	public IIcon topTexture;
	public Block block;

	public BlockGeyser(Block block, String name) {
		super(Material.rock);
		setBlockName(Constants.MODID + "_" + name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(block.getLightValue());
		this.needsRandomTick = true;
		this.block = block;
		GameRegistry.registerBlock(this, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
		this.topTexture = p_149651_1_.registerIcon(this.getTextureName() + "_top");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.topTexture : this.blockIcon;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!this.turnOn(world, x, y, z, rand)) {
			this.turnOff(world, x, y, z);
		}
	}

	private boolean turnOn(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (meta == 1) {
			randomMeta(world, x, y, z, rand);
		}
		meta = world.getBlockMetadata(x, y, z) + 1;
		System.out.println(meta);
		if (world.getBlock(x, y + meta - 1, z) == Blocks.air && rand.nextInt(meta) == 0) {
			for (int i = 1; i < meta + 1; i++) {
				world.setBlock(x, y + i, z, block);
			}
			return true;
		}
		return false;
	}

	private boolean forceOn(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (world.getBlock(x, y + meta - 1, z) == Blocks.air) {
			for (int i = 1; i < meta + 1; i++) {
				world.setBlock(x, y + i, z, block);
			}
			return true;
		}
		return false;
	}

	private boolean turnOff(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (world.getBlock(x, y + meta - 1, z) == block) {
			for (int i = 1; i < meta + 1; i++) {
				world.setBlock(x, y + i, z, Blocks.air);
			}
			return true;
		}
		return false;
	}

	private void randomMeta(World world, int x, int y, int z, Random rand) {
		world.setBlockMetadataWithNotify(x, y, z, rand.nextInt(15), 3);
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9) {
		if (!this.turnOff(w, x, y, z) && p.capabilities.isCreativeMode) {
			return this.forceOn(w, x, y, z);
		}
		return false;
	}
}
