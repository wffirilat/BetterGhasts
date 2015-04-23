package wffirilat.betterghasts.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityVoidFireball extends EntityFireball {

	public EntityVoidFireball(World p_i1761_1_, EntityLivingBase p_i1761_2_,
			double p_i1761_3_, double p_i1761_5_, double p_i1761_7_) {
		super(p_i1761_1_, p_i1761_2_, p_i1761_3_, p_i1761_5_, p_i1761_7_);
	}

	@Override
	protected void onImpact(MovingObjectPosition pos) {
		if (!this.worldObj.isRemote) {
			if (pos.entityHit != null) {
				if (pos.entityHit instanceof EntityLivingBase
						&& !((EntityLivingBase) pos.entityHit)
								.isPotionActive(Potion.harm)) {
					((EntityLivingBase) pos.entityHit).addPotionEffect(new PotionEffect(
							Potion.harm.id, 1, 1));
				}
			}

			this.setDead();
		}
	}

}
