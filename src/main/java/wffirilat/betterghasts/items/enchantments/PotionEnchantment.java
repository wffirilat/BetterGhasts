package wffirilat.betterghasts.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class PotionEnchantment extends Enchantment {
	
	public static int potionId;

	protected PotionEnchantment(int p_i1926_1_, int p_i1926_2_, int potionId) {
		super(p_i1926_1_, p_i1926_2_, EnumEnchantmentType.weapon);
		this.potionId = potionId;
	}
}
