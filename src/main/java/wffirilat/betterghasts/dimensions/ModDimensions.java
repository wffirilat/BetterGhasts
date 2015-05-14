package wffirilat.betterghasts.dimensions;

import net.minecraftforge.common.DimensionManager;
import wffirilat.betterghasts.dimensions.underdark.WorldProviderUnderdark;

public class ModDimensions {

	public static int underdarkId = 8;

	public static void init() {

		DimensionManager.registerProviderType(underdarkId, WorldProviderUnderdark.class, true);
		DimensionManager.registerDimension(underdarkId, underdarkId);

	}

}
