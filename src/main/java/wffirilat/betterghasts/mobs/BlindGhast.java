package wffirilat.betterghasts.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import wffirilat.betterghasts.items.ModItems;

public class BlindGhast extends EntityGhast {

	private Entity targetedEntity;
	private int explosionStrength = 0;
	private int aggroCooldown;

	public BlindGhast(World world) {
		super(world);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	@Override
	protected void updateEntityActionState() {
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}

		this.despawnEntity();
		this.prevAttackCounter = this.attackCounter;
		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		if (d3 < 1.0D || d3 > 3600.0D) {
			this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
		}

		if (this.courseChangeCooldown-- <= 0) {
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
				this.motionX += d0 / d3 * 0.2D;
				this.motionY += d1 / d3 * 0.2D;
				this.motionZ += d2 / d3 * 0.2D;
			} else {
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}

		if (this.targetedEntity != null && this.targetedEntity.isDead) {
			this.targetedEntity = null;
		}

		if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
			this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

			if (this.targetedEntity != null) {
				this.aggroCooldown = 20;
			}
		}

		double d4 = 64.0D;

		if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4) {
			double d5 = this.targetedEntity.posX - this.posX;
			double d6 = this.targetedEntity.posY - this.posY;
			double d7 = this.targetedEntity.posZ - this.posZ;
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(d5, d7)) * 180.0F / (float) Math.PI;

			if (this.canEntityBeSeen(this.targetedEntity)) {
				if (this.attackCounter == 10) {
					this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1007, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
				}

				++this.attackCounter;

				if (this.attackCounter == 20) {
					if (targetedEntity instanceof EntityLivingBase && !((EntityLivingBase) targetedEntity).isPotionActive(Potion.blindness)) {
						((EntityLivingBase) targetedEntity).addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 2));
					}
					this.shoot();
				}
			} else if (this.attackCounter > 0) {
				--this.attackCounter;
			}
		} else {
			this.renderYawOffset = this.rotationYaw = -((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI;

			if (this.attackCounter > 0) {
				--this.attackCounter;
			}
		}

		if (!this.worldObj.isRemote) {
			byte b1 = this.dataWatcher.getWatchableObjectByte(16);
			byte b0 = (byte) (this.attackCounter > 10 ? 1 : 0);

			if (b1 != b0) {
				this.dataWatcher.updateObject(16, Byte.valueOf(b0));
			}
		}
	}

	private boolean isCourseTraversable(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_) {
		double d4 = (this.waypointX - this.posX) / p_70790_7_;
		double d5 = (this.waypointY - this.posY) / p_70790_7_;
		double d6 = (this.waypointZ - this.posZ) / p_70790_7_;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; i < p_70790_7_; ++i) {
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public void shoot() {
		double offset = 4.0D;
		Vec3 lookVec = this.getLook(1.0F);
		double dx = targetedEntity.posX - (this.posX + lookVec.xCoord * offset);
		double dy = targetedEntity.boundingBox.minY + targetedEntity.height / 2.0F - (0.5D + this.posY + this.height / 2.0F);
		double dz = targetedEntity.posZ - (this.posZ + lookVec.zCoord * offset);
		this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1008, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
		EntitySmallFireball fireball = new EntitySmallFireball(worldObj, this, dx, dy, dz);
		fireball.posX = this.posX + lookVec.xCoord * offset;
		fireball.posY = this.posY + this.height / 2.0F + 0.5D;
		fireball.posZ = this.posZ + lookVec.zCoord * offset;
		fireball.accelerationX *= 1.5D;
		fireball.accelerationY *= 1.5D;
		fireball.accelerationZ *= 1.5D;
		worldObj.spawnEntityInWorld(fireball);
		this.attackCounter = -40;
	}

	@Override
	protected void dropFewItems(boolean player, int looting) {
		this.dropItem(ModItems.flameGhastTear, 1);
		super.dropFewItems(player, looting);
		super.dropFewItems(player, looting);

	}

}
