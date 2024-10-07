package net.laser.elixir.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.laser.elixir.Elixir;
import net.laser.elixir.block.custom.AlchemicalApparatusBlock;
import net.laser.elixir.item.ModItems;
import net.laser.elixir.recipe.AlchemicalApparatusRecipe;
import net.laser.screen.AlchemicalApparatusScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AlchemicalApparatusBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    private static final int VESSEL_SLOT = 0;
    private static final int FIRST_SLOT = 5;
    private static final int SECOND_SLOT = 2;
    private static final int THIRD_SLOT = 3;
    private static final int FOURTH_SLOT = 4;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;


    private int progress = 0;
    private int maxProgress = 72;



    public AlchemicalApparatusBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALCHEMICAL_APPARATUS_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch(index){
                    case 0 -> AlchemicalApparatusBlockEntity.this.progress;
                    case 1 -> AlchemicalApparatusBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index){
                    case 0 -> AlchemicalApparatusBlockEntity.this.progress = value;
                    case 1 -> AlchemicalApparatusBlockEntity.this.maxProgress = value;

                }
            }

            @Override
            public int size() {
                return 6;
            }
        };
    }

    public ItemStack getRenderStack(){
        if(this.getStack(OUTPUT_SLOT).isEmpty()){
            return this.getStack(VESSEL_SLOT);
        }else{
            return this.getStack(OUTPUT_SLOT);
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Alchemical Apparatus");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("alchemical_apparatus.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("alchemical_apparatus.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlchemicalApparatusScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()){
            return;
        }

        if(isOutputSlotEmptyOrReceivable()){
            if(this.hasRecipe()){
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(craftingHasFinished()){
                    this.craftItem();
                    this.resetProgress();

                }else{
                    //this.resetProgress();
                    markDirty(world, pos, state);
                }

            }else{
                this.resetProgress();
                markDirty(world,pos,state);

            }

        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {

        Optional<RecipeEntry<AlchemicalApparatusRecipe>> recipe = getCurrentRecipe();

        this.removeStack(VESSEL_SLOT, 1);
        Elixir.LOGGER.info("Craft");
        ItemStack result = new ItemStack(ModItems.ARCANE_SHARD);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean craftingHasFinished() {
        Elixir.LOGGER.info("Finished Crafting");
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<AlchemicalApparatusRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null)) &&
                canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<AlchemicalApparatusRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++){
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(AlchemicalApparatusRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == item;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || (this.getStack(OUTPUT_SLOT).getCount() <= this.getStack(OUTPUT_SLOT).getMaxCount());
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
