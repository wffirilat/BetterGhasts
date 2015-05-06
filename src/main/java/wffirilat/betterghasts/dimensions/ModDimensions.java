package wffirilat.betterghasts.dimensions;

import wffirilat.betterghasts.dimensions.underdark.WorldProviderUnderdark;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
	
	public static int underdarkId = 8;
	
	public static void init() {

		DimensionManager.registerProviderType(underdarkId, WorldProviderUnderdark.class, true);
		DimensionManager.registerDimension(underdarkId, underdarkId);
		
	}

}
