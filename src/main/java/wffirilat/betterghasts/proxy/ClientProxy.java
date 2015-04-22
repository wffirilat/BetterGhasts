package wffirilat.betterghasts.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import wffirilat.betterghasts.mobs.FlameGhast;
import wffirilat.betterghasts.mobs.RenderFlameGhast;

public class ClientProxy extends CommonProxy{
	
	public void registerRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(FlameGhast.class, new RenderFlameGhast());
		
	}

}
