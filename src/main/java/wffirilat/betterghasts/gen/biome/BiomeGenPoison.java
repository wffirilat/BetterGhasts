package wffirilat.betterghasts.gen.biome;

import java.awt.Color;

import net.minecraft.world.biome.BiomeGenJungle;

public class BiomeGenPoison extends BiomeGenJungle {
	
	public int color = Color.magenta.getRGB();

	public BiomeGenPoison(int p_i1971_1_) {
		super(p_i1971_1_, false);
		this.waterColorMultiplier = color;
	}

	public int getSkyColorByTemp(float temp) {
		return color;
	}

	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
		return color;
	}
	
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
		return color;
	}

}
