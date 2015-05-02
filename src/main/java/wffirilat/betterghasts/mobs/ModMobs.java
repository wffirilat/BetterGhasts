package wffirilat.betterghasts.mobs;

import net.minecraft.entity.EntityList;
import wffirilat.betterghasts.BetterGhasts;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModMobs {
	
	public static void init() {
		
		createEntity(FlameGhast.class, "flameGhast", 0x150a07, 0x4f271a);
		createEntity(PoisonGhast.class, "poisonGhast", 0x150a07, 0x4f271a);
		createEntity(BlindGhast.class, "blindGhast", 0x150a07, 0x4f271a);
		createEntity(CreepyGhast.class, "creepyGhast", 0x150a07, 0x4f271a);
		createEntity(VoidGhast.class, "voidGhast", 0x150a07, 0x4f271a);
		createEntity(BlazeGhast.class, "blazeGhast", 0x150a07, 0x4f271a);
		//TODO 5- ender ghast
		//TODO 8- homing ghast?
		
		//TODO 5- retextured fireballs?
		
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
