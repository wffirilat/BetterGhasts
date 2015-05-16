package wffirilat.betterghasts.items.enchantments;

import net.minecraft.potion.Potion;

public class ModEnchantments {

	public static PotionEnchantment slowEnchant;

	public static void init() {
		slowEnchant = new PotionEnchantment(22, 5, Potion.moveSlowdown.id);
	}

}
