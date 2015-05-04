package wffirilat.betterghasts.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import wffirilat.betterghasts.blocks.ModBlocks;

public class WorldGenGeyser extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (world.getBlock(x, y, z) == ModBlocks.ash) {
			for (int dx = -1; dx <= 1; dx++) {
				for (int dz = -1; dz <= 1; dz++) {
					world.setBlock(x + dx, y, z + dz, Blocks.air);
					world.setBlock(x + dx, y - 1, z + dz, ModBlocks.ash);

				}
			}
			if (rand.nextInt(3) == 0) {
				world.setBlock(x, y - 1, z, ModBlocks.lavaGeyser, rand.nextInt(15), 3);
			} else {
				world.setBlock(x, y - 1, z, ModBlocks.geyser, rand.nextInt(15), 3);
			}
			return true;
		}
		return false;
	}

}
