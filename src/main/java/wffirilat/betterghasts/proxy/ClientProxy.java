package wffirilat.betterghasts.proxy;

import wffirilat.betterghasts.mobs.FlameGhast;
import wffirilat.betterghasts.mobs.PoisonGhast;
import wffirilat.betterghasts.mobs.RenderFlameGhast;
import wffirilat.betterghasts.mobs.RenderPoisonGhast;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public void registerRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(FlameGhast.class, new RenderFlameGhast());
		RenderingRegistry.registerEntityRenderingHandler(PoisonGhast.class, new RenderPoisonGhast());

	}

}
