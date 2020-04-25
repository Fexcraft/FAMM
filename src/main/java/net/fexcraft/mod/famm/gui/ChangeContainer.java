package net.fexcraft.mod.famm.gui;

import net.fexcraft.lib.mc.gui.GenericContainer;
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
		//
	}

}
