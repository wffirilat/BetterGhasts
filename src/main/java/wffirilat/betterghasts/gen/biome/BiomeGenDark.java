package wffirilat.betterghasts.gen.biome;

import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.mobs.BlindGhast;
import wffirilat.betterghasts.mobs.FlameGhast;
import wffirilat.betterghasts.mobs.PoisonGhast;
import wffirilat.betterghasts.mobs.VoidGhast;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenDark extends BiomeGenJungle {

	public BiomeGenDark(int p_i1971_1_) {
		super(p_i1971_1_, true);
		this.waterColorMultiplier = 0;
		this.spawnableMonsterList.add(new SpawnListEntry(VoidGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(BlindGhast.class, 40, 1, 1));
		this.topBlock = ModBlocks.shadow;
		this.fillerBlock = ModBlocks.shadow;
	}
	
	public int getSkyColorByTemp(float temp) {
		return 0;
	}
}