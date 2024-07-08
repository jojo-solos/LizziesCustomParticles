package net.jojosolos.lizziescp.item.custom;

import net.minecraft.entity.LivingEntity;
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

    public SelectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            // initialize variables
            if(!user.getStackInHand(hand).hasNbt()) {
                user.getStackInHand(hand).setNbt(new NbtCompound());
            }
            ItemStack itemStack = user.getStackInHand(hand);
            NbtCompound newNbt = new NbtCompound();

            // go forward or backward based on crouch
            if(user.isSneaking())
                newNbt.putInt("lizziescp.nbt_particle", (itemStack.getNbt().getInt("lizziescp.nbt_particle")) - 1);
            else
                 newNbt.putInt("lizziescp.nbt_particle", (itemStack.getNbt().getInt("lizziescp.nbt_particle")) + 1);


            // setting the name of the item based on the new particle selected
            user.getStackInHand(hand).setCustomName(Text.literal("Selector ")
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
            default:
                newNbt.putInt("lizziescp.nbt_particle", 0);
                newName = "Red Cherry Particles";
        }
        itemStack.setNbt(newNbt);
        return newName;
    }


}
