package wffirilat.betterghasts.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderUnderdark extends WorldProvider {

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.swampland, 0.1f);
		this.dimensionId = ModDimensions.dimensionId;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderUnderdark(worldObj, worldObj.getSeed(), true);
	}

	public String getDimensionName() {
		return "Underdark";
	}
}
