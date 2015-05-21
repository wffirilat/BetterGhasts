package wffirilat.betterghasts.blocks;

import java.util.Random;

import net.minecraft.block.BlockAir;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import wffirilat.betterghasts.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class PoisonAir extends BlockAir {

	public String name = "poisonAir";

	public PoisonAir() {

		super();

		setBlockName(Constants.MODID + "_" + name);
		setBlockTextureName(Constants.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(this, name);

		this.needsRandomTick = true;

	}

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e) {
		if (e instanceof EntityLivingBase && !((EntityLivingBase) e).isPotionActive(Potion.poison)) {
			((EntityLivingBase) e).addPotionEffect(new PotionEffect(Potion.poison.id, 20, 1));
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		world.setBlock(x, y, z, Blocks.air);
	}
}
