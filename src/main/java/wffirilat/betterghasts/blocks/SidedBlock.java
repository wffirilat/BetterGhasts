package wffirilat.betterghasts.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SidedBlock extends ModBlockBase {

	public IIcon topTexture;
	public IIcon sideTexture;

	public SidedBlock(Material mat, String name) {
		super(mat, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
		this.topTexture = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.sideTexture = p_149651_1_.registerIcon(this.getTextureName() + "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.topTexture : side == 0 ? this.blockIcon : this.sideTexture;
	}

}
