package wffirilat.betterghasts.gen.biome;

import net.minecraft.world.biome.BiomeGenJungle;
import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.mobs.BlindGhast;
import wffirilat.betterghasts.mobs.VoidGhast;

public class BiomeGenDark extends BiomeGenJungle {

	public BiomeGenDark(int p_i1971_1_) {
		super(p_i1971_1_, true);
		this.waterColorMultiplier = 0;
		this.spawnableMonsterList.add(new SpawnListEntry(VoidGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(BlindGhast.class, 40, 1, 1));
		this.topBlock = ModBlocks.shadow;
		this.fillerBlock = ModBlocks.shadow;
	}

	@Override
	public int getSkyColorByTemp(float temp) {
		return 0;
	}
}
