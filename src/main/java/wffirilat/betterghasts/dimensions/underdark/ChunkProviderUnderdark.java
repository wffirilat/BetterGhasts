package wffirilat.betterghasts.dimensions.underdark;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.eventhandler.Event.Result;

//TODO Deobfuscate this monstrosity
public class ChunkProviderUnderdark implements IChunkProvider {
	private Random rand;
	/** A NoiseGeneratorOctaves used in generating nether terrain */
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	/** Determines whether slowsand or gravel can be generated at a location */
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	/**
	 * Determines whether something other than nettherack can be generated at a
	 * location
	 */
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves noiseGen7;
	/** Is the world that the nether is getting generated. */
	private World worldObj;
	private double[] noiseField;
	public MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
	/**
	 * Holds the noise used to determine whether slowsand can be generated at a
	 * location
	 */
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	/**
	 * Holds the noise used to determine whether something other than netherrack
	 * can be generated at a location
	 */
	private double[] netherrackExclusivityNoise = new double[256];
	private MapGenBase netherCaveGenerator = new MapGenUnderdarkCaves();
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	private static final String __OBFID = "CL_00000392";

	{
		this.genNetherBridge = (MapGenNetherBridge) TerrainGen.getModdedMapGen(this.genNetherBridge, NETHER_BRIDGE);
		this.netherCaveGenerator = TerrainGen.getModdedMapGen(this.netherCaveGenerator, NETHER_CAVE);
	}

	public ChunkProviderUnderdark(World world, long seed) {
		this.worldObj = world;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
		this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen7 = new NoiseGeneratorOctaves(this.rand, 16);

		NoiseGenerator[] noiseGens = { this.noiseGen1, this.noiseGen2, this.noiseGen3, this.slowsandGravelNoiseGen, this.netherrackExculsivityNoiseGen, this.noiseGen6, this.noiseGen7 };
		noiseGens = TerrainGen.getModdedNoiseGenerators(world, this.rand, noiseGens);
		this.noiseGen1 = (NoiseGeneratorOctaves) noiseGens[0];
		this.noiseGen2 = (NoiseGeneratorOctaves) noiseGens[1];
		this.noiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
		this.slowsandGravelNoiseGen = (NoiseGeneratorOctaves) noiseGens[3];
		this.netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves) noiseGens[4];
		this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
		this.noiseGen7 = (NoiseGeneratorOctaves) noiseGens[6];
	}

	public void generateBlockArray(int chunkX, int chunkZ, Block[] blocks) {
		byte scale = 4;
		byte lavaLevel = 32;
		int xSize = scale + 1;
		byte ySize = 17;
		int zSize = scale + 1;
		this.noiseField = this.initializeNoiseField(this.noiseField, chunkX * scale, 0, chunkZ * scale, xSize, ySize, zSize);

		for (int x1 = 0; x1 < scale; ++x1) {
			for (int z1 = 0; z1 < scale; ++z1) {
				for (int y1 = 0; y1 < 16; ++y1) {
					double d0 = 0.125D;
					double ixyz = this.noiseField[((x1 + 0) * zSize + z1 + 0) * ySize + y1 + 0];
					double ixyZ = this.noiseField[((x1 + 0) * zSize + z1 + 1) * ySize + y1 + 0];
					double iXyz = this.noiseField[((x1 + 1) * zSize + z1 + 0) * ySize + y1 + 0];
					double iXyZ = this.noiseField[((x1 + 1) * zSize + z1 + 1) * ySize + y1 + 0];
					double ixYz = (this.noiseField[((x1 + 0) * zSize + z1 + 0) * ySize + y1 + 1] - ixyz) * d0;
					double ixYZ = (this.noiseField[((x1 + 0) * zSize + z1 + 1) * ySize + y1 + 1] - ixyZ) * d0;
					double iXYz = (this.noiseField[((x1 + 1) * zSize + z1 + 0) * ySize + y1 + 1] - iXyz) * d0;
					double iXYZ = (this.noiseField[((x1 + 1) * zSize + z1 + 1) * ySize + y1 + 1] - iXyZ) * d0;

					for (int i1 = 0; i1 < 8; ++i1) {
						double d10 = ixyz;
						double d11 = ixyZ;
						double d12 = (iXyz - ixyz) / 4.0D;
						double d13 = (iXyZ - ixyZ) / 4.0D;

						for (int j1 = 0; j1 < 4; ++j1) {
							int index = j1 + x1 * 4 << 11 | 0 + z1 * 4 << 7 | y1 * 8 + i1;
							short short1 = 128;
							double d15 = d10;
							double d16 = (d11 - d10) / 4.0D;

							for (int k2 = 0; k2 < 4; ++k2) {
								Block block = null;

								if (y1 * 8 + i1 < lavaLevel) {
									block = Blocks.lava;
								}

								if (d15 > 0.0D) {
									block = Blocks.stone;
								}

								blocks[index] = block;
								index += short1;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						ixyz += ixYz;
						ixyZ += ixYZ;
						iXyz += iXYz;
						iXyZ += iXYZ;
					}
				}
			}
		}
	}

	@Deprecated
	// You should provide metadata and biome data in the below method
	public void func_147418_b(int chunkX, int chunkZ, Block[] blocks) {
		this.replaceBiomeBlocks(chunkX, chunkZ, blocks, new byte[blocks.length], null);
	}

	public void replaceBiomeBlocks(int chunkX, int chunkZ, Block[] blocks, byte[] meta, BiomeGenBase[] biomes) {
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, blocks, meta, biomes, this.worldObj);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY) {
			return;
		}

		byte b0 = 64;
		double d0 = 0.03125D;
		this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
		this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, d0, 1.0D, d0);
		this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
				boolean flag = this.slowsandNoise[k + l * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean flag1 = this.gravelNoise[k + l * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				int i1 = (int) (this.netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int j1 = -1;
				Block block = Blocks.stone;
				Block block1 = Blocks.stone;

				for (int k1 = 127; k1 >= 0; --k1) {
					int l1 = (l * 16 + k) * 128 + k1;

					if (k1 < 127 - this.rand.nextInt(5) && k1 > 0 + this.rand.nextInt(5)) {
						Block block2 = blocks[l1];

						if (block2 != null && block2.getMaterial() != Material.air) {
							if (block2 == Blocks.stone) {
								if (j1 == -1) {
									if (i1 <= 0) {
										block = null;
										block1 = Blocks.stone;
									} else if (k1 >= b0 - 4 && k1 <= b0 + 1) {
										block = Blocks.stone;
										block1 = Blocks.stone;

										if (flag1) {
											block = Blocks.gravel;
											block1 = Blocks.stone;
										}

										if (flag) {
											block = Blocks.soul_sand;
											block1 = Blocks.soul_sand;
										}
									}

									if (k1 < b0 && (block == null || block.getMaterial() == Material.air)) {
										block = Blocks.air;
									}

									j1 = i1;

									if (k1 >= b0 - 1) {
										blocks[l1] = block;
									} else {
										blocks[l1] = block1;
									}
								} else if (j1 > 0) {
									--j1;
									blocks[l1] = block1;
								}
							}
						} else {
							j1 = -1;
						}
					} else {
						blocks[l1] = Blocks.bedrock;
					}
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int chunkX, int chunkZ) {
		return this.provideChunk(chunkX, chunkZ);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it
	 * will generates all the blocks for the specified chunk from the map seed
	 * and chunk seed
	 */
	@Override
	public Chunk provideChunk(int chunkX, int chunkZ) {
		this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
		Block[] blocks = new Block[16 * 16 * 128];
		byte[] meta = new byte[blocks.length];
		BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, chunkX * 16, chunkZ * 16, 16, 16);
		// Forge Move up to allow for passing to replaceBiomeBlocks
		this.generateBlockArray(chunkX, chunkZ, blocks);
		this.replaceBiomeBlocks(chunkX, chunkZ, blocks, meta, abiomegenbase);
		this.netherCaveGenerator.func_151539_a(this, this.worldObj, chunkX, chunkZ, blocks);
		this.genNetherBridge.func_151539_a(this, this.worldObj, chunkX, chunkZ, blocks);
		Chunk chunk = new Chunk(this.worldObj, blocks, meta, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();

		for (int k = 0; k < abyte.length; ++k) {
			abyte[k] = (byte) abiomegenbase[k].biomeID;
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the
	 * [empty] noise array, the position, and the size.
	 */
	private double[] initializeNoiseField(double[] noisefield, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, noisefield, x, y, z, sizeX, sizeY, sizeZ);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY) {
			return event.noisefield;
		}

		if (noisefield == null) {
			noisefield = new double[sizeX * sizeY * sizeZ];
		}

		double scaleXZ = 684.412D / 2D;
		double scaleY = 2053.236D / 2D;
		this.noiseData4 = this.noiseGen6.generateNoiseOctaves(this.noiseData4, x, y, z, sizeX, 1, sizeZ, 1.0D, 0.0D, 1.0D);
		this.noiseData5 = this.noiseGen7.generateNoiseOctaves(this.noiseData5, x, y, z, sizeX, 1, sizeZ, 100.0D, 0.0D, 100.0D);
		this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, x, y, z, sizeX, sizeY, sizeZ, scaleXZ / 80.0D, scaleY / 60.0D, scaleXZ / 80.0D);
		this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, x, y, z, sizeX, sizeY, sizeZ, scaleXZ, scaleY, scaleXZ);
		this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, x, y, z, sizeX, sizeY, sizeZ, scaleXZ, scaleY, scaleXZ);
		int k1 = 0;
		int i = 0;
		double[] adouble1 = new double[sizeY];
		int y1;

		for (y1 = 0; y1 < sizeY; ++y1) {
			adouble1[y1] = Math.cos(y1 * Math.PI * 6.0D / sizeY) * 2.0D;
			double d2 = y1;

			if (y1 > sizeY / 2) {
				d2 = sizeY - 1 - y1;
			}

			if (d2 < 4.0D) {
				d2 = 4.0D - d2;
				adouble1[y1] -= d2 * d2 * d2 * 10.0D;
			}
		}

		for (int x1 = 0; x1 < sizeX; ++x1) {
			for (int z1 = 0; z1 < sizeZ; ++z1) {
				double d3 = (this.noiseData4[i] + 256.0D) / 512.0D;

				if (d3 > 1.0D) {
					d3 = 1.0D;
				}

				double d4 = 0.0D;
				double d5 = this.noiseData5[i] / 8000.0D;

				if (d5 < 0.0D) {
					d5 = -d5;
				}

				d5 = d5 * 3.0D - 3.0D;

				if (d5 < 0.0D) {
					d5 /= 2.0D;

					if (d5 < -1.0D) {
						d5 = -1.0D;
					}

					d5 /= 1.4D;
					d5 /= 2.0D;
					d3 = 0.0D;
				} else {
					if (d5 > 1.0D) {
						d5 = 1.0D;
					}

					d5 /= 6.0D;
				}

				d3 += 0.5D;
				d5 = d5 * sizeY / 16.0D;
				++i;

				for (int j2 = 0; j2 < sizeY; ++j2) {
					double d6 = 0.0D;
					double d7 = adouble1[j2];
					double d8 = this.noiseData2[k1] / 512.0D;
					double d9 = this.noiseData3[k1] / 512.0D;
					double d10 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;

					if (d10 < 0.0D) {
						d6 = d8;
					} else if (d10 > 1.0D) {
						d6 = d9;
					} else {
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;
					double d11;

					if (j2 > sizeY - 4) {
						d11 = (j2 - (sizeY - 4)) / 3.0F;
						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					if (j2 < d4) {
						d11 = (d4 - j2) / 4.0D;

						if (d11 < 0.0D) {
							d11 = 0.0D;
						}

						if (d11 > 1.0D) {
							d11 = 1.0D;
						}

						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					noisefield[k1] = d6;
					++k1;
				}
			}
		}

		return noisefield;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	@Override
	public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	@Override
	public void populate(IChunkProvider p_73153_1_, int chunkX, int chunkZ) {

		int x = chunkX * 16;
		int z = chunkZ * 16;
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(p_73153_1_, this.worldObj, this.rand, chunkX, chunkZ, false));
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.worldObj, this.rand, x, z));
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.worldObj, this.rand, x, z));
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(p_73153_1_, this.worldObj, this.rand, chunkX, chunkZ, false));

	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If
	 * passed false, save up to two chunks. Return true if all chunks have been
	 * saved.
	 */
	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk. Not saved during autosave,
	 * only during world unload. Currently unimplemented.
	 */
	@Override
	public void saveExtraData() {
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to
	 * unload every such chunk.
	 */
	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	@Override
	public boolean canSave() {
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	@Override
	public String makeString() {
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the
	 * given location.
	 */
	@Override
	public List getPossibleCreatures(EnumCreatureType type, int x, int y, int z) {
		if (type == EnumCreatureType.monster) {
			if (this.genNetherBridge.hasStructureAt(x, y, z)) {
				return this.genNetherBridge.getSpawnList();
			}

			if (this.genNetherBridge.func_142038_b(x, y, z) && this.worldObj.getBlock(x, y - 1, z) == Blocks.nether_brick) {
				return this.genNetherBridge.getSpawnList();
			}
		}

		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(x, z);
		return biomegenbase.getSpawnableList(type);
	}

	@Override
	public ChunkPosition func_147416_a(World world, String name, int x, int y, int z) {
		return null;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(int chunkX, int chunkZ) {
		this.genNetherBridge.func_151539_a(this, this.worldObj, chunkX, chunkZ, (Block[]) null);
	}
}
