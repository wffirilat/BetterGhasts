package wffirilat.betterghasts.gen.biome;

import java.awt.Color;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.world.biome.BiomeGenBase;
import wffirilat.betterghasts.blocks.ModBlocks;

public class BiomeGenVolcano extends BiomeGenBase {

	public BiomeGenVolcano(int id) {
		super(id);
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 200, 2, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 100, 2, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 100, 2, 10));
		this.spawnableCreatureList.clear();
		this.theBiomeDecorator = new BiomeDecoratorVolcano();
		this.topBlock = ModBlocks.ash;
		this.fillerBlock = ModBlocks.basalt;
		this.rootHeight = 0.3f;
		this.heightVariation = 0.0f;
	}

	public int getSkyColorByTemp(float temp) {
		return Color.red.getRGB();
	}

}
