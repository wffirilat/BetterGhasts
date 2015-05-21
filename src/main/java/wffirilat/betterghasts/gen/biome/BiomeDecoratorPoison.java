package wffirilat.betterghasts.gen.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import wffirilat.betterghasts.gen.WorldGenPoisonJet;

public class BiomeDecoratorPoison extends BiomeDecorator {

	public int poisonJetChance = 1;
	public WorldGenerator jets = new WorldGenPoisonJet();

	public BiomeDecoratorPoison() {
		super();
	}

	@Override
	protected void genDecorations(BiomeGenBase biome) {
		if (this.randomGenerator.nextInt(this.poisonJetChance) == 0) {
			int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.jets.generate(currentWorld, randomGenerator, x, currentWorld.getHeightValue(x, z), z);
		}
		super.genDecorations(biome);

	}

}
