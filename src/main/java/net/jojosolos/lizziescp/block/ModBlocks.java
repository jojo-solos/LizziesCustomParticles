package net.jojosolos.lizziescp.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jojosolos.lizziescp.LizziesCustomParticles;
import net.jojosolos.lizziescp.block.custom.ParticleBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PARTICLE_BLOCK = registerBlock("particle_block",
            new ParticleBlock(FabricBlockSettings.create()));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(LizziesCustomParticles.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(LizziesCustomParticles.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        LizziesCustomParticles.LOGGER.info("Registering ModBlocks for " + LizziesCustomParticles.MODID);
    }
}
