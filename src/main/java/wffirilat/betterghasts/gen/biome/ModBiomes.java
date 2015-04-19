package wffirilat.betterghasts.gen.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class ModBiomes {
	
	public static BiomeGenBase volcanoBiome;
	public static BiomeGenBase poisonBiome;
	

	public static void init() {
		poisonBiome = new BiomeGenPoison(137).setBiomeName("Poison").setColor(3470985).func_76733_a(3470985).setTemperatureRainfall(0.95F, 0.9F);
		volcanoBiome = new BiomeGenVolcano(138).setBiomeName("Volcano Planes").setTemperatureRainfall(2.0f, 0.0f);
		
		BiomeDictionary.registerBiomeType(poisonBiome, Type.SWAMP);
		BiomeDictionary.registerBiomeType(volcanoBiome, Type.FOREST);
		BiomeManager.addSpawnBiome(volcanoBiome);
		BiomeManager.addSpawnBiome(poisonBiome);
		
	}

}
