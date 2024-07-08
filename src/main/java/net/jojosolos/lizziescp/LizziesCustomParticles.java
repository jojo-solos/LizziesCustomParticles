package net.jojosolos.lizziescp;

import net.fabricmc.api.ModInitializer;

import net.jojosolos.lizziescp.block.ModBlocks;
import net.jojosolos.lizziescp.item.ModItemGroups;
import net.jojosolos.lizziescp.item.ModItems;
import net.jojosolos.lizziescp.particles.ModParticle;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LizziesCustomParticles implements ModInitializer {
	public static final String MODID = "lizziescp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final Identifier TOGGLE_PARTICLE = new Identifier(MODID, "toggle_particle");

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();

		ModBlocks.registerModBlocks();
		ModParticle.registerParticle();

	}
} 