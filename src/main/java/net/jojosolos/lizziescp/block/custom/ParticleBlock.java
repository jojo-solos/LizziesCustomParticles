package net.jojosolos.lizziescp.block.custom;

import net.jojosolos.lizziescp.LizziesCustomParticlesClient;
import net.jojosolos.lizziescp.item.ModItems;
import net.jojosolos.lizziescp.particles.ModParticle;
import net.minecraft.block.*;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ParticleBlock extends Block implements Waterloggable {
    protected static final VoxelShape OFF_SHAPE = VoxelShapes.empty();
	protected static final VoxelShape ON_SHAPE = Block.createCuboidShape(0D, 0D, 0D, 16D, 16D, 16.0D);

	public static final BooleanProperty PART_ON = BooleanProperty.of("part_on");
    public static final IntProperty PARTICLE_TYPE = IntProperty.of("part_type", 0, 6);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static Boolean TOGGLED_PART = LizziesCustomParticlesClient.TOGGLE_PART;

    public ParticleBlock(Settings settings) {
        super(settings
                .noCollision()
                .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                .strength(-1.0F, 3600000.0F)
        );
        setDefaultState(getDefaultState()
                .with(PART_ON, false)
                .with(WATERLOGGED, false)
                .with(PARTICLE_TYPE, 0)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.getItem().equals(ModItems.SELECTOR)) {
                world.setBlockState(pos, state.with(PARTICLE_TYPE, itemStack.getNbt().getInt("lizziescp.nbt_particle")));
            } else {
            }
        }

        return ActionResult.SUCCESS;
    }

    private DefaultParticleType setParticle(Integer x) {
        switch(x) {
            case(0):
                return ModParticle.RED_CHERRY_PARTICLES;
            case(1):
                return ParticleTypes.CHERRY_LEAVES;
            case(2):
                return ParticleTypes.CAMPFIRE_COSY_SMOKE;
            case(3):
                return ParticleTypes.BUBBLE_POP;
            case(4):
                return ParticleTypes.ASH;
            case(5):
                return ParticleTypes.CLOUD;
            case(6):
                return ParticleTypes.EXPLOSION;
            default:
                return ModParticle.RED_CHERRY_PARTICLES;
        }
    }

    @Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
        if(TOGGLED_PART) {
            if(state.get(PARTICLE_TYPE) == 0 || state.get(PARTICLE_TYPE) == 1) {
                if (random.nextInt(10) == 0) {
                    ParticleUtil.spawnParticle(world, pos.up(), random, setParticle(state.get(PARTICLE_TYPE)));
                }
            } else if(state.get(PARTICLE_TYPE) == 2) {
                world.addImportantParticle(
                    ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                    true,
                    (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                    (double)pos.getY() + random.nextDouble() + random.nextDouble(),
                    (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                    0.0,
                    0.07,
                    0.0
                );
            } else
                ParticleUtil.spawnParticle(world, pos.up(), random, setParticle(state.get(PARTICLE_TYPE)));
		}
	}

    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(TOGGLED_PART) {
            return OFF_SHAPE;
        } else return ON_SHAPE;
	}

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(PART_ON, WATERLOGGED, PARTICLE_TYPE);
	}

    @Override
	public BlockRenderType getRenderType(BlockState state) {
        if(TOGGLED_PART) {
            return BlockRenderType.INVISIBLE;
        } else {
            return BlockRenderType.MODEL;
        }
	}


    // waterlogged overrides

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState()
            .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            // This is for 1.17 and below: world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

}

