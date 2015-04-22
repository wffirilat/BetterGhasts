package wffirilat.betterghasts.events;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import wffirilat.betterghasts.gen.biome.ModBiomes;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TerrainEvents {
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void stopVolcanoLakes(PopulateChunkEvent.Populate event) {
		if (event.type == PopulateChunkEvent.Populate.EventType.LAKE) {
			if (event.world.getBiomeGenForCoords(event.chunkX * 16, event.chunkZ * 16) == ModBiomes.volcanoBiome) {
				event.setResult(Result.DENY);
				//System.out.println("canceling lakes");

			}
		}
	}

}
