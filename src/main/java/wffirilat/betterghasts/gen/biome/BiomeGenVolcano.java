package wffirilat.betterghasts.gen.biome;

import java.awt.Color;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.world.biome.BiomeGenBase;
import wffirilat.betterghasts.blocks.ModBlocks;
import wffirilat.betterghasts.mobs.BlazeGhast;
import wffirilat.betterghasts.mobs.CreepyGhast;
import wffirilat.betterghasts.mobs.FlameGhast;

public class BiomeGenVolcano extends BiomeGenBase {

	public BiomeGenVolcano(int id) {
		super(id);
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(FlameGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(BlazeGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(CreepyGhast.class, 40, 1, 1));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 400, 2, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 100, 2, 10));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 100, 2, 10));
		this.spawnableCreatureList.clear();
		this.theBiomeDecorator = new BiomeDecoratorVolcano();
		this.topBlock = ModBlocks.ash;
		this.fillerBlock = ModBlocks.basalt;
		this.rootHeight = 0.3f;
		this.heightVariation = 0.0f;
	}

	@Override
	public int getSkyColorByTemp(float temp) {
		return Color.red.getRGB();
	}

}
