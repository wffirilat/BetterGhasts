package wffirilat.betterghasts.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GhastRage extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon topTexture;
	@SideOnly(Side.CLIENT)
	private IIcon bottomTexture;
	private static final String __OBFID = "CL_00000324";

	public GhastRage(String name) {
		super(Material.tnt);
		this.setBlockName(Constants.MODID + "_" + name);
		this.setBlockTextureName(Constants.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? this.bottomTexture : side == 1 ? this.topTexture : this.blockIcon;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);

		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			this.onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
			this.onBlockDestroyedByPlayer(world, x, y, z, 1);
			world.setBlockToAir(x, y, z);
		}
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	/**
	 * Called upon the block being destroyed by an explosion
	 */
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		if (!world.isRemote) {
			PrimedGhastRage PrimedGhastRage = new PrimedGhastRage(world, x + 0.5F, y + 0.5F, z + 0.5F, explosion.getExplosivePlacedBy());
			PrimedGhastRage.fuse = world.rand.nextInt(PrimedGhastRage.fuse / 4) + PrimedGhastRage.fuse / 8;
			world.spawnEntityInWorld(PrimedGhastRage);
		}
	}

	/**
	 * Called right before the block is destroyed by a player. Args: world, x,
	 * y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		this.explode(world, x, y, z, meta, (EntityLivingBase) null);
	}

	public void explode(World world, int x, int y, int z, int doExplode, EntityLivingBase entity) {
		if (!world.isRemote) {
			if ((doExplode & 1) == 1) // is doExplode right?
			{
				PrimedGhastRage PrimedGhastRage = new PrimedGhastRage(world, x + 0.5F, y + 0.5F, z + 0.5F, entity);
				world.spawnEntityInWorld(PrimedGhastRage);
				world.playSoundAtEntity(PrimedGhastRage, "game.tnt.primed", 1.0F, 1.0F);
			}
		}
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sideX, float sideY, float sideZ) {
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
			this.explode(world, x, y, z, 1, player);
			world.setBlockToAir(x, y, z);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		} else {
			return super.onBlockActivated(world, x, y, z, player, side, sideX, sideY, sideZ);
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityArrow && !world.isRemote) {
			EntityArrow entityarrow = (EntityArrow) entity;

			if (entityarrow.isBurning()) {
				this.explode(world, x, y, z, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase) entityarrow.shootingEntity : null);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	/**
	 * Return whether this block can drop from an explosion.
	 */
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.blockIcon = registry.registerIcon(this.getTextureName() + "_side");
		this.topTexture = registry.registerIcon(this.getTextureName() + "_top");
		this.bottomTexture = registry.registerIcon(this.getTextureName() + "_bottom");
	}
}
