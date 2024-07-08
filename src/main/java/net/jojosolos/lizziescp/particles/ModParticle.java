package net.jojosolos.lizziescp.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jojosolos.lizziescp.LizziesCustomParticles;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticle {

    public static final DefaultParticleType RED_CHERRY_PARTICLES = FabricParticleTypes.simple();

    public static void registerParticle() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(LizziesCustomParticles.MODID, "red_cherry_particle"),
                RED_CHERRY_PARTICLES);
    }
}
