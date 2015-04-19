package wffirilat.betterghasts.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ModBlocks {
	
	

	public static Block poisonAir;
	public static Block poisonJet;
	public static Block deadGrass;
	public static Block ash;
	public static Block basalt;
	public static Block geyser;
	public static Block lavaGeyser;

	public static void init() {
		
		poisonJet = new PoisonJet().setHardness(0.5F);
		deadGrass = new DeadGrass().setHardness(0.5F);
		poisonAir = new PoisonAir();
		ash = new BlockAsh().setHardness(0.5f).setResistance(10.0f).setStepSound(Block.soundTypeSand);
		basalt = new ModBlockBase("basalt").setHardness(1.5f).setResistance(15.0f);
		geyser = new BlockGeyser(Blocks.water, "geyser").setHardness(1.5f).setResistance(15.0f);
		lavaGeyser = new BlockGeyser(Blocks.lava, "lavaGeyser").setHardness(1.5f).setResistance(15.0f);
		
		//TODO 3- speed boost air
		//TODO 2- entity.stepHeight air?

	}

}
