package wffirilat.betterghasts.gen.biome;

import net.minecraft.world.biome.BiomeGenJungle;

public class BiomeGenPoison extends BiomeGenJungle {

	public int color0 = 4227072;
	public int color1 = 32768;
	public int color2 = 32832;

	public BiomeGenPoison(int p_i1971_1_) {
		super(p_i1971_1_, true);
		this.waterColorMultiplier = color0;
		this.theBiomeDecorator = new BiomeDecoratorPoison();
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 25;
		this.theBiomeDecorator.flowersPerChunk = 4;
	}

	@Override
	public int getSkyColorByTemp(float temp) {
		return color0;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z) {
		double d0 = plantNoise.func_151601_a(x * 0.0225D, z * 0.0225D);
		return d0 < -0.1D ? color0 : d0 > 0.1d ? color2 : color1;
	}

	@Override
	public int getBiomeGrassColor(int x, int y, int z) {
		double d0 = plantNoise.func_151601_a(x * 0.0225D, z * 0.0225D);
		return d0 < -0.3D ? color0 : d0 > 0.3d ? color2 : color1;
	}

}
