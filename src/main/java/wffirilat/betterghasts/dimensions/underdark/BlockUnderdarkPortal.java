package wffirilat.betterghasts.dimensions.underdark;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.dimensions.ModDimensions;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockUnderdarkPortal extends BlockBreakable {

	protected BlockUnderdarkPortal(String name, Material material, boolean p_i45411_3_) {
		super(name, material, p_i45411_3_);
	}

	public static String name = "underdarkPortal";
	public static final int[][] directions = new int[][] { new int[0], { 3, 1 }, { 2, 0 } };

	public BlockUnderdarkPortal() {
		super(Constants.MODID + ":" + name, Material.portal, false);
		this.setTickRandomly(true);
		this.setBlockName(Constants.MODID + "_" + name);
		this.setBlockTextureName(Constants.MODID + ":" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);

		if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < world.difficultySetting.getDifficultyId()) {
			int l;

			for (l = y; !World.doesBlockHaveSolidTopSurface(world, x, l, z) && l > 0; --l) {
				;
			}

			if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube()) {
				Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, x + 0.5D, l + 1.1D, z + 0.5D);

				if (entity != null) {
					entity.timeUntilPortal = entity.getPortalCooldown();
				}
			}
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int l = getPortalDirection(world.getBlockMetadata(x, y, z));

		if (l == 0) {
			if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
				l = 2;
			} else {
				l = 1;
			}

			if (world instanceof World && !((World) world).isRemote) {
				((World) world).setBlockMetadataWithNotify(x, y, z, l, 2);
			}
		}

		float f = 0.125F;
		float f1 = 0.125F;

		if (l == 1) {
			f = 0.5F;
		}

		if (l == 2) {
			f1 = 0.5F;
		}

		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean func_150000_e(World world, int x, int y, int z) {
		BlockUnderdarkPortal.Size size = new BlockUnderdarkPortal.Size(world, x, y, z, 1);
		BlockUnderdarkPortal.Size size1 = new BlockUnderdarkPortal.Size(world, x, y, z, 2);

		if (size.func_150860_b() && size.field_150864_e == 0) {
			size.func_150859_c();
			return true;
		} else if (size1.func_150860_b() && size1.field_150864_e == 0) {
			size1.func_150859_c();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		int dir = getPortalDirection(world.getBlockMetadata(x, y, z));
		BlockUnderdarkPortal.Size size = new BlockUnderdarkPortal.Size(world, x, y, z, 1);
		BlockUnderdarkPortal.Size size1 = new BlockUnderdarkPortal.Size(world, x, y, z, 2);

		if (dir == 1 && (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g)) {
			world.setBlock(x, y, z, Blocks.air);
		} else if (dir == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g)) {
			world.setBlock(x, y, z, Blocks.air);
		} else if (dir == 0 && !size.func_150860_b() && !size1.func_150860_b()) {
			world.setBlock(x, y, z, Blocks.air);
		}
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if
	 * the adjacent block is at the given coordinates. Args: blockAccess, x, y,
	 * z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		int dir = 0;

		if (world.getBlock(x, y, z) == this) {
			dir = getPortalDirection(world.getBlockMetadata(x, y, z));

			if (dir == 0) {
				return false;
			}

			if (dir == 2 && side != 5 && side != 4) {
				return false;
			}

			if (dir == 1 && side != 3 && side != 2) {
				return false;
			}
		}

		boolean flag = world.getBlock(x - 1, y, z) == this && world.getBlock(x - 2, y, z) != this;
		boolean flag1 = world.getBlock(x + 1, y, z) == this && world.getBlock(x + 2, y, z) != this;
		boolean flag2 = world.getBlock(x, y, z - 1) == this && world.getBlock(x, y, z - 2) != this;
		boolean flag3 = world.getBlock(x, y, z + 1) == this && world.getBlock(x, y, z + 2) != this;
		boolean flag4 = flag || flag1 || dir == 1;
		boolean flag5 = flag2 || flag3 || dir == 2;
		return flag4 && side == 4 ? true : flag4 && side == 5 ? true : flag5 && side == 2 ? true : flag5 && side == 3;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the
	 * block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
			EntityPlayerMP thePlayer = (EntityPlayerMP) entity;
			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = 10;
			} else if (thePlayer.dimension != ModDimensions.underdarkId) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, ModDimensions.underdarkId, new TeleporterUnderdark(thePlayer.mcServer.worldServerForDimension(ModDimensions.underdarkId)));
			}

			else if (thePlayer.dimension == ModDimensions.underdarkId) {
				thePlayer.timeUntilPortal = 10;
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterUnderdark(thePlayer.mcServer.worldServerForDimension(0)));
			}
		}
	}

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1
	 * for alpha
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	/**
	 * A randomly called display update to be able to add particles or other
	 * items for display
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(100) == 0) {
			world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for (int l = 0; l < 4; ++l) {
			double d0 = x + rand.nextFloat();
			double d1 = y + rand.nextFloat();
			double d2 = z + rand.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			d5 = (rand.nextFloat() - 0.5D) * 0.5D;

			if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
				d0 = x + 0.5D + 0.25D * i1;
				d3 = rand.nextFloat() * 2.0F * i1;
			} else {
				d2 = z + 0.5D + 0.25D * i1;
				d5 = rand.nextFloat() * 2.0F * i1;
			}

			world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}

	public static int getPortalDirection(int meta) {
		return meta & 3;
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemById(0);
	}

	public static class Size {
		private final World worldObj;
		private final int dir;
		private final int field_150866_c;
		private final int field_150863_d;
		private int field_150864_e = 0;
		private ChunkCoordinates coords;
		private int field_150862_g;
		private int field_150868_h;
		private static final String __OBFID = "CL_00000285";

		public Size(World world, int x, int y, int z, int dir) {
			this.worldObj = world;
			this.dir = dir;
			this.field_150863_d = BlockUnderdarkPortal.directions[dir][0];
			this.field_150866_c = BlockUnderdarkPortal.directions[dir][1];

			for (int i1 = y; y > i1 - 21 && y > 0 && this.func_150857_a(world.getBlock(x, y - 1, z)); --y) {
				;
			}

			int j1 = this.func_150853_a(x, y, z, this.field_150863_d) - 1;

			if (j1 >= 0) {
				this.coords = new ChunkCoordinates(x + j1 * Direction.offsetX[this.field_150863_d], y, z + j1 * Direction.offsetZ[this.field_150863_d]);
				this.field_150868_h = this.func_150853_a(this.coords.posX, this.coords.posY, this.coords.posZ, this.field_150866_c);

				if (this.field_150868_h < 2 || this.field_150868_h > 21) {
					this.coords = null;
					this.field_150868_h = 0;
				}
			}

			if (this.coords != null) {
				this.field_150862_g = this.func_150858_a();
			}
		}

		protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
			int j1 = Direction.offsetX[p_150853_4_];
			int k1 = Direction.offsetZ[p_150853_4_];
			int i1;
			Block block;

			for (i1 = 0; i1 < 22; ++i1) {
				block = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);

				if (!this.func_150857_a(block)) {
					break;
				}

				Block block1 = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);

				if (block1 != Blocks.stonebrick) {
					break;
				}
			}

			block = this.worldObj.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
			return block == Blocks.stonebrick ? i1 : 0;
		}

		protected int func_150858_a() {
			int i;
			int j;
			int k;
			int l;
			label56:

				for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g) {
					i = this.coords.posY + this.field_150862_g;

					for (j = 0; j < this.field_150868_h; ++j) {
						k = this.coords.posX + j * Direction.offsetX[BlockPortal.field_150001_a[this.dir][1]];
						l = this.coords.posZ + j * Direction.offsetZ[BlockPortal.field_150001_a[this.dir][1]];
						Block block = this.worldObj.getBlock(k, i, l);

						if (!this.func_150857_a(block)) {
							break label56;
						}

						if (block == ModBlocks.portal) {
							++this.field_150864_e;
						}

						if (j == 0) {
							block = this.worldObj.getBlock(k + Direction.offsetX[BlockPortal.field_150001_a[this.dir][0]], i, l + Direction.offsetZ[BlockPortal.field_150001_a[this.dir][0]]);

							if (block != Blocks.stonebrick) {
								break label56;
							}
						} else if (j == this.field_150868_h - 1) {
							block = this.worldObj.getBlock(k + Direction.offsetX[BlockPortal.field_150001_a[this.dir][1]], i, l + Direction.offsetZ[BlockPortal.field_150001_a[this.dir][1]]);

							if (block != Blocks.stonebrick) {
								break label56;
							}
						}
					}
				}

			for (i = 0; i < this.field_150868_h; ++i) {
				j = this.coords.posX + i * Direction.offsetX[BlockPortal.field_150001_a[this.dir][1]];
				k = this.coords.posY + this.field_150862_g;
				l = this.coords.posZ + i * Direction.offsetZ[BlockPortal.field_150001_a[this.dir][1]];

				if (this.worldObj.getBlock(j, k, l) != Blocks.stonebrick) {
					this.field_150862_g = 0;
					break;
				}
			}

			if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
				return this.field_150862_g;
			} else {
				this.coords = null;
				this.field_150868_h = 0;
				this.field_150862_g = 0;
				return 0;
			}
		}

		protected boolean func_150857_a(Block p_150857_1_) {
			return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == Blocks.fire || p_150857_1_ == ModBlocks.portal;
		}

		public boolean func_150860_b() {
			return this.coords != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
		}

		public void func_150859_c() {
			for (int i = 0; i < this.field_150868_h; ++i) {
				int j = this.coords.posX + Direction.offsetX[this.field_150866_c] * i;
				int k = this.coords.posZ + Direction.offsetZ[this.field_150866_c] * i;

				for (int l = 0; l < this.field_150862_g; ++l) {
					int i1 = this.coords.posY + l;
					this.worldObj.setBlock(j, i1, k, ModBlocks.portal, this.dir, 2);
				}
			}
		}
	}
}