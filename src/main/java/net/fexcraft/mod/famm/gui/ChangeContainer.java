package net.fexcraft.mod.famm.gui;

import net.fexcraft.lib.mc.gui.GenericContainer;
import net.fexcraft.mod.famm.blocks.FAMMBLKH;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.fexcraft.mod.famm.items.FAMMItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public class ChangeContainer extends GenericContainer {
	
	protected BlockPos pos;

	public ChangeContainer(EntityPlayer player, int x, int y, int z){
		super(player);
		pos = new BlockPos(x, y, z);
	}

	@Override
	protected void packet(Side side, NBTTagCompound packet, EntityPlayer player){
		if(packet.hasKey("block")){
			if(player.getHeldItemMainhand().getItem() != FAMMItems.ink) return;
			IBlockState state = player.world.getBlockState(pos);
			boolean half = state.getBlock() instanceof FAMMBLKH;
			Block newblk = FAMMBlocks.get(packet.getString("block") + (half ? "_hb" : ""));
			IBlockState newstate = newblk.getDefaultState();
			if(half){
				newstate = newstate.withProperty(FAMMBLKH.FACING, state.getValue(FAMMBLKH.FACING));
				newstate = newstate.withProperty(FAMMBLKH.DEPTH, state.getValue(FAMMBLKH.DEPTH));
			}
			player.world.setBlockState(pos, newstate, 2);
			if(!player.capabilities.isCreativeMode){
				player.getHeldItemMainhand().shrink(1);
			}
		}
	}

}
