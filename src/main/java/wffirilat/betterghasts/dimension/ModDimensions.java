package wffirilat.betterghasts.dimension;

import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
	
	public static void init() {
		registerUnderdark();
	}
	
	public static final int dimensionId = 8;

	public static void registerUnderdark() {
		
		DimensionManager.registerProviderType(dimensionId, WorldProviderUnderdark.class, false);
		DimensionManager.registerDimension(dimensionId, dimensionId);
		
		
	}

}
