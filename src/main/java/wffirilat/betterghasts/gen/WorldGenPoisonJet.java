package wffirilat.betterghasts.gen;

import java.util.Random;

import wffirilat.betterghasts.blocks.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPoisonJet extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		for (int dx = -5; dx <= 5; dx++) {
			for (int dz = -5; dz <= 5; dz++) {
				y = world.getHeightValue(x + dx, z + dz);
				while (world.getBlock(x + dx, y, z + dz) != Blocks.grass && y > 62) {
					y--;
				}
				if(y == 62 && world.getBlock(x + dx, y, z + dz) != Blocks.grass){
					return false;
				}
				if (dx == 0 && dz == 0) {
					world.setBlock(x, y, z, ModBlocks.poisonJet);
				} else if (dx * dx + dz * dz < 25 && world.getBlock(x + dx, y, z + dz) == Blocks.grass) {
					world.setBlock(x + dx, y, z + dz, ModBlocks.deadGrass);
				}
			}
		}
		return true;
	}

}
