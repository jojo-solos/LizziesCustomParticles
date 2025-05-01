package net.jojosolos.lizziescp.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SelectorItem extends Item {

    NbtCompound newNbt = new NbtCompound();
    NbtComponent component = NbtComponent.of(newNbt);

    public SelectorItem(Settings settings) {
        super(settings);
        settings.component(DataComponentTypes.CUSTOM_DATA, component);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            ItemStack itemStack = user.getStackInHand(hand);

            // initialize variables
            if(!itemStack.getComponents().contains(DataComponentTypes.CUSTOM_DATA)) {
                newNbt.putInt("lizziescp.nbt_particle", 0);
                itemStack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(newNbt));
            }

            // go forward or backward based on crouch
            if (user.isSneaking()) {
                newNbt.putInt("lizziescp.nbt_particle", (itemStack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getInt("lizziescp.nbt_particle") - 1));
            } else {
                newNbt.putInt("lizziescp.nbt_particle", (itemStack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getInt("lizziescp.nbt_particle") + 1));
            }
            user.getStackInHand(hand).set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(newNbt));
            // setting the name of the item based on the new particle selected
            user.getStackInHand(hand).set(DataComponentTypes.CUSTOM_NAME, Text.literal("Selector ")
                    .append(Text.literal(setParticle(itemStack, newNbt)).formatted(Formatting.AQUA)));

            world.playSound(null, user.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.MASTER, 0.5f, 1f);
            user.setCurrentHand(hand);

        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private String setParticle(ItemStack itemStack, NbtCompound newNbt) {
        String newName = "";
        switch(newNbt.getInt("lizziescp.nbt_particle")) {
            case(0):
                // name the item
                newName = "Red Cherry Particles";
                newNbt.putInt("lizziescp.nbt_particle", 0);
                break;
            case(1):
                newName = "Pink Cherry Particles";
                newNbt.putInt("lizziescp.nbt_particle", 1);
                break;
            case(2):
                newName = "Smoke Particles";
                newNbt.putInt("lizziescp.nbt_particle", 2);
                break;
            case(3):
                newName = "Bubbles Particles";
                newNbt.putInt("lizziescp.nbt_particle", 3);
                break;
            case(4):
                newName = "Ash Particles";
                newNbt.putInt("lizziescp.nbt_particle", 4);
                break;
            case(5):
                newName = "Cloud Particles";
                newNbt.putInt("lizziescp.nbt_particle", 5);
                break;
            case(6):
                newName = "Explosion Particles";
                newNbt.putInt("lizziescp.nbt_particle", 6);
                break;
            case(7):
                newName = "Spore Blossom Particles";
                newNbt.putInt("lizziescp.nbt_particle", 7);
                break;
            case(8):
                newName = "Enchant Particles";
                newNbt.putInt("lizziescp.nbt_particle", 8);
                break;
            case(9):
                newName = "Portal Particles";
                newNbt.putInt("lizziescp.nbt_particle", 9);
                break;
            case(10):
                newName = "Enchant Particles 2";
                newNbt.putInt("lizziescp.nbt_particle", 10);
                break;
            default:
                newNbt.putInt("lizziescp.nbt_particle", 0);
                newName = "Red Cherry Particles";
        }
        itemStack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(newNbt));
        return newName;
    }


}
