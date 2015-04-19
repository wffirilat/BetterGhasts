package wffirilat.betterghasts.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import wffirilat.betterghasts.dimension.ModDimensions;
import wffirilat.betterghasts.dimension.TeleporterUnderdark;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class UnderdarkPortal extends BlockPortal {

	public String name = "voidPortal";

	public UnderdarkPortal() {

		super();
		setBlockName(Constants.MODID + "_" + name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);

	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP)))
		{
			EntityPlayerMP player = (EntityPlayerMP) par5Entity;
			MinecraftServer mServer = MinecraftServer.getServer();

			if (player.timeUntilPortal > 0)
			{
				player.timeUntilPortal = 10;
			}
			else if (player.dimension != ModDimensions.dimensionId)
			{
				player.timeUntilPortal = 10;

				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ModDimensions.dimensionId, new TeleporterUnderdark(mServer.worldServerForDimension(ModDimensions.dimensionId)));
			}
			else
			{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterUnderdark(mServer.worldServerForDimension(1)));
			}
		}
	}
	
	@Override
	public boolean func_150000_e(World par1World, int par2, int par3, int par4)
	{
//		byte b0 = 0;
//		byte b1 = 0;
//
//		if (par1World.getBlock(par2 - 1, par3, par4) == Blocks.bedrock || par1World.getBlock(par2 + 1, par3, par4) == Blocks.bedrock)
//		{
//			b0 = 1;
//		}
//
//		if (par1World.getBlock(par2, par3, par4 - 1) == Blocks.bedrock || par1World.getBlock(par2, par3, par4 + 1) == Blocks.bedrock)
//		{
//			b1 = 1;
//		}
//
//		if (b0 == b1)
//		{
//			return false;
//		}
//		else
//		{
//			if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
//			{
//				par2 -= b0;
//				par4 -= b1;
//			}
//
//			int l;
//			int i1;
//
//			for (l = -1; l <= 2; ++l)
//			{
//				for (i1 = -1; i1 <= 3; ++i1)
//				{
//					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
//
//					if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
//					{
//						Block j1 = par1World.getBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
//						boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);
//
//						if (flag)
//						{
//							if (j1 != Blocks.bedrock)
//							{
//								return false;
//							}
//						}
//						else if (!isAirBlock && j1 != Blocks.fire)
//						{
//							return false;
//						}
//					}
//				}
//			}
//
//			for (l = 0; l < 2; ++l)
//			{
//				for (i1 = 0; i1 < 3; ++i1)
//				{
//					par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, ModBlocks.voidPortal, 0, 2);
//				}
//			}
//
			return true;
//		}
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
	{
//		byte b0 = 0;
//		byte b1 = 1;
//
//		if (w.getBlock(x - 1, y, z) == this || w.getBlock(x + 1, y, z) == this)
//		{
//			b0 = 1;
//			b1 = 0;
//		}
//
//		int i1;
//
//		for (i1 = y; w.getBlock(x, i1 - 1, z) == this; --i1)
//		{
//			;
//		}
//
//		if (w.getBlock(x, i1 - 1, z) != Blocks.bedrock)
//		{
//			w.setBlockToAir(x, y, z);
//		}
//		else
//		{
//			int j1;
//
//			for (j1 = 1; j1 < 4 && w.getBlock(x, i1 + j1, z) == this; ++j1)
//			{
//				;
//			}
//
//			if (j1 == 3 && w.getBlock(x, i1 + j1, z) == Blocks.bedrock)
//			{
//				boolean flag = w.getBlock(x - 1, y, z) == this || w.getBlock(x + 1, y, z) == this;
//				boolean flag1 = w.getBlock(x, y, z - 1) == this || w.getBlock(x, y, z + 1) == this;
//
//				if (flag && flag1)
//				{
//					w.setBlockToAir(x, y, z);
//				}
//				else
//				{
//					if ((w.getBlock(x + b0, y, z + b1) != Blocks.bedrock || w.getBlock(x - b0, y, z - b1) != this) && (w.getBlock(x - b0, y, z - b1) != Blocks.bedrock || w.getBlock(x + b0, y, z + b1) != this))
//					{
//						w.setBlockToAir(x, y, z);
//					}
//				}
//			}
//			else
//			{
//				w.setBlockToAir(x, y, z);
//			}
//		}
	}
}