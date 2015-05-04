package wffirilat.betterghasts.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpikes extends WorldGenerator {
	public Block block;
	public int minHeight;
	public int variation;
	public int spireChance;
	public int spireHeightMax;
	public int heightOffset;

	public WorldGenSpikes(Block block) {
		this(block, 7, 4);
	}

	public WorldGenSpikes(Block block, int minHeight, int variation) {
		this(block, minHeight, variation, 60, 30);
	}

	public WorldGenSpikes(Block block, int minHeight, int variation, int spireChance, int spireHeightMax) {
		this(block, minHeight, variation, spireChance, spireHeightMax, 0);
	}

	public WorldGenSpikes(Block block, int minHeight, int variation, int spireChance, int spireHeightMax, int heightOffset) {
		super();
		this.block = block;
		this.minHeight = minHeight;
		this.variation = variation;
		this.spireChance = spireChance;
		this.spireHeightMax = spireHeightMax;
		this.heightOffset = heightOffset;
	}

	public boolean generate(World world, Random random, int x, int y, int z) {
		while (world.isAirBlock(x, y, z) && y > 2) {
			--y;
		}
		y += random.nextInt(4) + this.heightOffset;
		int height = random.nextInt(this.variation) + this.minHeight;
		int width = height / 4 + random.nextInt(2);

		if (width > 1 && random.nextInt(this.spireChance) == 0) {
			y += 10 + random.nextInt(this.spireHeightMax);
		}

		int y1;
		int k1;
		int x1;

		for (y1 = 0; y1 < height; ++y1) {
			float f = (1.0F - (float) y1 / (float) height) * (float) width;
			k1 = MathHelper.ceiling_float_int(f);

			for (x1 = -k1; x1 <= k1; ++x1) {
				float f1 = (float) MathHelper.abs_int(x1) - 0.25F;

				for (int z1 = -k1; z1 <= k1; ++z1) {
					float f2 = (float) MathHelper.abs_int(z1) - 0.25F;

					if ((x1 == 0 && z1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (x1 != -k1 && x1 != k1 && z1 != -k1 && z1 != k1 || random.nextFloat() <= 0.75F)) {
						Block block = world.getBlock(x + x1, y + y1, z + z1);
						this.func_150515_a(world, x + x1, y + y1, z + z1, this.block);

						if (y1 != 0 && k1 > 1) {
							block = world.getBlock(x + x1, y - y1, z + z1);
							this.func_150515_a(world, x + x1, y - y1, z + z1, this.block);
						}
					}
				}
			}
		}

		y1 = width - 1;

		if (y1 < 0) {
			y1 = 0;
		} else if (y1 > 1) {
			y1 = 1;
		}

		for (int j2 = -y1; j2 <= y1; ++j2) {
			k1 = -y1;

			while (k1 <= y1) {
				x1 = y - 1;
				int k2 = 50;

				if (Math.abs(j2) == 1 && Math.abs(k1) == 1) {
					k2 = random.nextInt(5);
				}

				while (true) {
					if (x1 > 50) {
						Block block1 = world.getBlock(x + j2, x1, z + k1);

						if (block1.getMaterial() == Material.air || block1 == Blocks.dirt || block1 == Blocks.snow || block1 == Blocks.ice || block1 == this.block) {
							this.func_150515_a(world, x + j2, x1, z + k1, this.block);
							--x1;
							--k2;

							if (k2 <= 0) {
								x1 -= random.nextInt(5) + 1;
								k2 = random.nextInt(5);
							}

							continue;
						}
					}

					++k1;
					break;
				}
			}
		}

		return true;

	}
}