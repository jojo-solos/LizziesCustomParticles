package net.jojosolos.lizziescp.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.jojosolos.lizziescp.LizziesCustomParticles;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModParticle {

    public static final SimpleParticleType RED_CHERRY_PARTICLES  =
            registerParticle("red_cherry_particle", FabricParticleTypes.simple());

    public static final SimpleParticleType FIREFLY_PARTICLES  =
            registerParticle("firefly_particle", FabricParticleTypes.simple());

    public static final SimpleParticleType FEATHER_PARTICLES  =
            registerParticle("feather_particle", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(LizziesCustomParticles.MODID, name), particleType);
    }

    public static void registerParticles() {
        LizziesCustomParticles.LOGGER.info("Registering Particles for lizziescp");
    }
}
