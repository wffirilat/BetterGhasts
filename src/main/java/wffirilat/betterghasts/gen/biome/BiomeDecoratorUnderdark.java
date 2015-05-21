package wffirilat.betterghasts.gen.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;
import wffirilat.betterghasts.gen.WorldGenGeyser;
import wffirilat.betterghasts.gen.WorldGenSpikes;

public class BiomeDecoratorUnderdark extends BiomeDecorator {

	public int obsidianSpikes;
	public WorldGenerator spikeGen;
	public int chunksPerGeyser;
	public WorldGenerator geyserGen;
	public WorldGenerator lakeGen;
	public int chunksPerLake;

	public BiomeDecoratorUnderdark() {
		super();
		this.obsidianSpikes = 1;
		this.generateLakes = false;
		this.chunksPerGeyser = 5;
		this.chunksPerLake = 10;
		this.geyserGen = new WorldGenGeyser();
		this.spikeGen = new WorldGenSpikes(Blocks.obsidian, 7, 8, 30, 15, 3);
		this.lakeGen = new WorldGenLakes(Blocks.lava);

	}

	@Override
	protected void genDecorations(BiomeGenBase biome) {
		super.genDecorations(biome);
		this.generateOres();
		if (this.randomGenerator.nextInt(this.chunksPerLake) == 0) {
			int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.lakeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		for (int i = 0; i < this.obsidianSpikes; ++i) {
			int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.spikeGen.generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getHeightValue(x, z), z);
		}
		if (this.randomGenerator.nextInt(this.chunksPerGeyser) == 0) {
			int x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			int y = this.currentWorld.getHeightValue(x, z);
			this.geyserGen.generate(this.currentWorld, this.randomGenerator, x, y - 1, z);
		}
	}
}
