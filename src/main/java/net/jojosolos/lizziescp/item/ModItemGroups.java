package net.jojosolos.lizziescp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jojosolos.lizziescp.LizziesCustomParticles;
import net.jojosolos.lizziescp.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup PARTICLES = Registry.register(Registries.ITEM_GROUP,
            new Identifier(LizziesCustomParticles.MODID, "lizziescp"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lizziescp"))
                    .icon(() -> new ItemStack(ModItems.SELECTOR)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SELECTOR);
                        entries.add(ModBlocks.PARTICLE_BLOCK);
                    }).build());


    public static void registerItemGroups() {
        LizziesCustomParticles.LOGGER.info("Registering Item Groups for " + LizziesCustomParticles.MODID);
    }
}
