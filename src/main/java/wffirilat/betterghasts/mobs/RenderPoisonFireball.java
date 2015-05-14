package wffirilat.betterghasts.mobs;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import wffirilat.betterghasts.items.ModItems;
import wffirilat.betterghasts.lib.Constants;

public class RenderPoisonFireball extends RenderFireball {

	public static final ResourceLocation texture = new ResourceLocation(Constants.MODID + ":" + "textures/models/poisonFireball.png");
	private float size;

	public RenderPoisonFireball(float size) {
		super(size);
		this.size = size;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFireball entity) {
		return this.texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityFireball) entity);
	}

	@Override
	public void doRender(EntityFireball p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		GL11.glPushMatrix();
		this.bindEntityTexture(p_76986_1_);
		GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		float f2 = this.size;
		GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
		IIcon iicon = ModItems.poisonFireball.getIconFromDamage(0);
		Tessellator tessellator = Tessellator.instance;
		float f3 = iicon.getMinU();
		float f4 = iicon.getMaxU();
		float f5 = iicon.getMinV();
		float f6 = iicon.getMaxV();
		float f7 = 1.0F;
		float f8 = 0.5F;
		float f9 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float xrot, float yrot) {
		this.doRender((EntityFireball) entity, x, y, z, xrot, yrot);
	}

}
