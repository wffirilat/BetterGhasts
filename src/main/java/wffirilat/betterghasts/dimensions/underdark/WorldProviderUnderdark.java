package wffirilat.betterghasts.dimensions.underdark;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import wffirilat.betterghasts.dimensions.ModDimensions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderUnderdark extends WorldProvider {
	private static final String __OBFID = "CL_00000387";

	/**
	 * creates a new world chunk manager for WorldProvider
	 */
	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerUnderdark(BiomeGenBase.mushroomIsland, 0.0F);
		this.isHellWorld = true;
		this.hasNoSky = true;
		this.dimensionId = ModDimensions.underdarkId;
	}

	/**
	 * Return Vec3D with biome specific fog color
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
		return Vec3.createVectorHelper(0, 0, 0);
	}

	@Override
	public String getWelcomeMessage() {
		return "Delving into the Underdark";
	}

	@Override
	public String getDepartMessage() {
		return "Climbing out of the Underdark";
	}

	@Override
	public double getMovementFactor() {
		return 0.125D;
	}

	/**
	 * Creates the light to brightness table
	 */
	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.1F;

		for (int level = 0; level <= 15; ++level) {
			float f1 = 1.0F - level / 15.0F;
			this.lightBrightnessTable[level] = ((1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f) / 10.0f;
		}
	}

	/**
	 * Returns a new chunk provider which generates chunks for this world
	 */
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderUnderdark(this.worldObj, this.worldObj.getSeed());
	}

	/**
	 * Returns 'true' if in the "main surface world", but 'false' if in the
	 * Nether or End dimensions.
	 */
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	/**
	 * Will check if the x, z position specified is alright to be set as the map
	 * spawn point
	 */
	@Override
	public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_) {
		return false;
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified
	 * time (usually worldTime)
	 */
	@Override
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
		return 0.5F;
	}

	/**
	 * True if the player can respawn in this dimension (true = overworld, false
	 * = nether).
	 */
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	/**
	 * Returns true if the given X,Z coordinate should show environmental fog.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
		return true;
	}

	/**
	 * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
	 */
	@Override
	public String getDimensionName() {
		return "Underdark";
	}
}