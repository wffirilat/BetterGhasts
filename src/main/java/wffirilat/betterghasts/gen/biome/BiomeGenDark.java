package wffirilat.betterghasts.gen.biome;

import wffirilat.betterghasts.mobs.BlindGhast;
import wffirilat.betterghasts.mobs.FlameGhast;
import wffirilat.betterghasts.mobs.PoisonGhast;
import wffirilat.betterghasts.mobs.VoidGhast;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenDark extends BiomeGenJungle {
	
	public int color0 = 4227072;
	public int color1 = 32768;
	public int color2 = 32832;

	public BiomeGenDark(int p_i1971_1_) {
		super(p_i1971_1_, true);
		this.waterColorMultiplier = color0;
		this.spawnableMonsterList.add(new SpawnListEntry(VoidGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(BlindGhast.class, 40, 1, 1));
		this.theBiomeDecorator = new BiomeDecoratorPoison();
		this.theBiomeDecorator.treesPerChunk=10;
		this.theBiomeDecorator.grassPerChunk = 25;
        this.theBiomeDecorator.flowersPerChunk = 4;
	}

	public int getSkyColorByTemp(float temp) {
		return color0;
	}

	public int getBiomeFoliageColor(int x, int y, int z) {
		double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
		return d0 < -0.1D ? color0 : (d0 > 0.1d ? color2 : color1);
	}
	
	public int getBiomeGrassColor(int x, int y, int z)
    {
        double d0 = plantNoise.func_151601_a((double)x * 0.0225D, (double)z * 0.0225D);
        return d0 < -0.3D ? color0 : (d0 > 0.3d ? color2 : color1);
    }

}
