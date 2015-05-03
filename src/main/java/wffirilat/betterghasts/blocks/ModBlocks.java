package wffirilat.betterghasts.blocks;

import wffirilat.betterghasts.dimension.BlockUnderdarkPortal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	

	public static Block poisonAir;
	public static Block poisonJet;
	public static Block deadGrass;
	public static Block portal;
	

	public static void init() {
		
		poisonJet = new PoisonJet().setHardness(0.5F);
		deadGrass = new DeadGrass().setHardness(0.5F);
		poisonAir = new PoisonAir();
		portal = new BlockUnderdarkPortal();
		
		//TODO 3- speed boost air
		//TODO 2- entity.stepHeight air?

	}

}
