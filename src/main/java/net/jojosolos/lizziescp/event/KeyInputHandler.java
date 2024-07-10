package net.jojosolos.lizziescp.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.jojosolos.lizziescp.LizziesCustomParticlesClient;
import net.jojosolos.lizziescp.block.custom.ParticleBlock;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String CATEGORY_KEY = "category.lizziescp.togglebutton";
    public static KeyBinding bindingParticleToggle;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(bindingParticleToggle.wasPressed()) {
                if(LizziesCustomParticlesClient.TOGGLE_PART) {
                    LizziesCustomParticlesClient.TOGGLE_PART = false;
                    ParticleBlock.TOGGLED_PART = false;
                }
                else {
                    LizziesCustomParticlesClient.TOGGLE_PART = true;
                    ParticleBlock.TOGGLED_PART = true;
                }

                client.worldRenderer.reload();
            }
        });
    }

    public static void register() {
        bindingParticleToggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.lizziescp.togglebutton",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                CATEGORY_KEY));

        registerKeyInputs();
    }
}
