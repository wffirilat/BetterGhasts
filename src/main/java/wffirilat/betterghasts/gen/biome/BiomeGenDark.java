package wffirilat.betterghasts.gen.biome;

import net.minecraft.world.biome.BiomeGenJungle;
import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.mobs.BlindGhast;
import wffirilat.betterghasts.mobs.VoidGhast;

public class BiomeGenDark extends BiomeGenJungle {

	public BiomeGenDark(int p_i1971_1_) {
		super(p_i1971_1_, true);
		this.spawnableMonsterList.add(new SpawnListEntry(VoidGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(BlindGhast.class, 40, 1, 1));
		this.theBiomeDecorator = new BiomeDecoratorPoison();
		this.topBlock = ModBlocks.shadow;
		this.fillerBlock = ModBlocks.shadow;
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 25;
		this.theBiomeDecorator.flowersPerChunk = 4;
	}
}