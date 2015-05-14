package wffirilat.betterghasts.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class PotionEnchantment extends Enchantment {

	public static int potionId;

	protected PotionEnchantment(int id, int rarity, int potionId) {
		super(id, rarity, EnumEnchantmentType.weapon);
		this.potionId = potionId;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}
}
