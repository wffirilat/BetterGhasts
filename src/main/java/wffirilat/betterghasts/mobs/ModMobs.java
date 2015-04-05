package wffirilat.betterghasts.mobs;

import net.minecraft.entity.EntityList;
import wffirilat.betterghasts.BetterGhasts;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModMobs {
	
	public static void init() {

		//TODO creepy ghast (More explosive) --1
		//TODO blaze ghast --1
		//TODO void ghast --3
		//TODO blinding ghast --3
		//TODO ender ghast --5
		//TODO homing ghast? --8
		
		//TODO retextured fireballs? --5
		
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor) {
		int randomID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomID);
		EntityRegistry.registerModEntity(entityClass, entityName, randomID, BetterGhasts.modInstance, 64, 1, true);

		createEgg(randomID, solidColor, spotColor);

	}

	private static void createEgg(int randomID, int solidColor, int spotColor) {
		EntityList.entityEggs.put(randomID, new EntityList.EntityEggInfo(randomID, solidColor, spotColor));
	}

}
