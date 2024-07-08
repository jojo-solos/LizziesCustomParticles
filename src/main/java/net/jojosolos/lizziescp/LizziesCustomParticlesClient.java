package net.jojosolos.lizziescp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.jojosolos.lizziescp.event.KeyInputHandler;
import net.jojosolos.lizziescp.particles.ModParticle;
import net.jojosolos.lizziescp.particles.custom.RedCherryParticle;

public class LizziesCustomParticlesClient implements ClientModInitializer {

    public static Boolean TOGGLE_PART = false;

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticle.RED_CHERRY_PARTICLES, RedCherryParticle.Factory::new);

        KeyInputHandler.register();
    }
}
