package wffirilat.betterghasts.dimension;

import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
	
	public static int underdarkId = 8;
	
	public static void init() {

		DimensionManager.registerProviderType(underdarkId, WorldProviderUnderdark.class, true);
		DimensionManager.registerDimension(underdarkId, underdarkId);
		
	}

}
