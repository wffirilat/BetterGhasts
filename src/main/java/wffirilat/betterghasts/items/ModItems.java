package wffirilat.betterghasts.items;

import net.minecraft.item.Item;

public class ModItems {

	public static Item flameGhastTear;
	public static Item poisonFireball;
	public static Item blazeGhastTear;
	public static Item poisonGhastTear;
	public static Item blindGhastTear;
	public static Item voidGhastTear;
	public static Item creepyGhastTear;
	public static Item EssencePoison;
	public static Item EssenceCreepy;
	public static Item EssenceVoid;
	public static Item EssenceBlind;
	public static Item EssenceBlaze;
	public static Item EssenceFlame;

	public static void init() {

		flameGhastTear = new ModItemBase("flameGhastTear");
		blazeGhastTear = new ModItemBase("blazeGhastTear");
		blindGhastTear = new ModItemBase("blindGhastTear");
		poisonFireball = new ModItemBase("poisonFireball");
		creepyGhastTear = new ModItemBase("creepyGhastTear");
		voidGhastTear = new ModItemBase("voidGhastTear");
		poisonGhastTear = new ModItemBase("poisonGhastTear");
		EssencePoison = new ModItemBase("EssencePoison");
		EssenceCreepy = new ModItemBase("EssenceCreepy");
		EssenceVoid = new ModItemBase("EssenceVoid");
		EssenceBlind = new ModItemBase("EssenceBlind");
		EssenceFlame = new ModItemBase("EssenceFlame");
		EssenceBlaze = new ModItemBase("EssenceBlaze");
		// TODO 2- more ghast tears

		// TODO 6- wet suit
		// TODO 7- flippers
		// TODO 8- goggles

	}

}
