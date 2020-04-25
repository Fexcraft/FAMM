package net.fexcraft.mod.famm.gui;

import net.fexcraft.lib.mc.gui.GenericGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ChangeGui extends GenericGui<ChangeContainer> {

	public ChangeGui(EntityPlayer player, int x, int y, int z){
		super(new ResourceLocation("famm/gui/change.png"), new ChangeContainer(player, x, y, z), player);
		//
	}

	@Override
	protected void init(){
		//
	}

	@Override
	protected void predraw(float pticks, int mouseX, int mouseY){
		//
	}

	@Override
	protected void drawbackground(float pticks, int mouseX, int mouseY){
		//
	}

	@Override
	protected boolean buttonClicked(int mouseX, int mouseY, int mouseButton, String key, BasicButton button){
		//
		return false;
	}

	@Override
	protected void scrollwheel(int am, int x, int y){
		//
	}

}
