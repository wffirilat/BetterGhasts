package wffirilat.betterghasts.gen.biome;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

public class BetterGhastsLayer extends GenLayer {
	private List<BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];

	private static final String __OBFID = "CL_00000555";

	public BetterGhastsLayer(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_) {
		super(p_i2122_1_);

		this.parent = p_i2122_3_;

		for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
			com.google.common.collect.ImmutableList<BiomeEntry> biomesToAdd = BiomeManager.getBiomes(type);
			int idx = type.ordinal();

			if (this.biomes[idx] == null) {
				this.biomes[idx] = new ArrayList<BiomeEntry>();
			}
			if (biomesToAdd != null) {
				this.biomes[idx].addAll(biomesToAdd);
			}
		}

		int desertIdx = BiomeManager.BiomeType.DESERT.ordinal();

		if (p_i2122_4_ == WorldType.DEFAULT_1_1) {
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.desert, 10));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.forest, 10));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.extremeHills, 10));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.swampland, 10));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.plains, 10));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.taiga, 10));
		} else {
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.desert, 30));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.savanna, 20));
			this.biomes[desertIdx].add(new BiomeEntry(BiomeGenBase.plains, 10));
			this.biomes[desertIdx].add(new BiomeEntry(ModBiomes.poisonBiome, 10));
			this.biomes[desertIdx].add(new BiomeEntry(ModBiomes.volcanoBiome, 10));
			this.biomes[desertIdx].add(new BiomeEntry(ModBiomes.darkBiome, 10));
		}
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be
	 * interpreted as temperatures, rainfall amounts, or biomeList[] indices
	 * based on the particular GenLayer subclass.
	 */
	@Override
	public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
		int[] aint = this.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
		int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

		for (int i1 = 0; i1 < p_75904_4_; ++i1) {
			for (int j1 = 0; j1 < p_75904_3_; ++j1) {
				this.initChunkSeed(j1 + p_75904_1_, i1 + p_75904_2_);
				int k1 = aint[j1 + i1 * p_75904_3_];
				int l1 = (k1 & 3840) >> 8;
			k1 &= -3841;

			if (isBiomeOceanic(k1)) {
				aint1[j1 + i1 * p_75904_3_] = k1;
			} else if (k1 == BiomeGenBase.mushroomIsland.biomeID) {
				aint1[j1 + i1 * p_75904_3_] = k1;
			} else if (k1 == 1) {
				if (l1 > 0) {
					if (this.nextInt(3) == 0) {
						aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.mesaPlateau.biomeID;
					} else {
						aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.mesaPlateau_F.biomeID;
					}
				} else {
					aint1[j1 + i1 * p_75904_3_] = this.getWeightedBiomeEntry(BiomeManager.BiomeType.DESERT).biome.biomeID;
				}
			} else if (k1 == 2) {
				if (l1 > 0) {
					aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.jungle.biomeID;
				} else {
					aint1[j1 + i1 * p_75904_3_] = this.getWeightedBiomeEntry(BiomeManager.BiomeType.WARM).biome.biomeID;
				}
			} else if (k1 == 3) {
				if (l1 > 0) {
					aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.megaTaiga.biomeID;
				} else {
					aint1[j1 + i1 * p_75904_3_] = this.getWeightedBiomeEntry(BiomeManager.BiomeType.COOL).biome.biomeID;
				}
			} else if (k1 == 4) {
				aint1[j1 + i1 * p_75904_3_] = this.getWeightedBiomeEntry(BiomeManager.BiomeType.ICY).biome.biomeID;
			} else {
				aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.mushroomIsland.biomeID;
			}
			}
		}

		return aint1;
	}

	protected BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type) {
		List<BiomeEntry> biomeList = this.biomes[type.ordinal()];
		int totalWeight = WeightedRandom.getTotalWeight(biomeList);
		int weight = BiomeManager.isTypeListModded(type) ? this.nextInt(totalWeight) : this.nextInt(totalWeight / 10) * 10;
		return (BiomeEntry) WeightedRandom.getItem(biomeList, weight);
	}
}
