package net.fexcraft.mod.famm.gui;

import org.lwjgl.opengl.GL11;

import net.fexcraft.lib.mc.gui.GenericGui;
import net.fexcraft.mod.famm.FAMM;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;

public class ChangeGui extends GenericGui<ChangeContainer> {
	
	private static ItemStack[] stacks;

	public ChangeGui(EntityPlayer player, int x, int y, int z){
		super(new ResourceLocation("famm:textures/gui/change.png"), new ChangeContainer(player, x, y, z), player);
		xSize = 194;
		ySize = 154;
		if(stacks == null){
			stacks = new ItemStack[FAMMBlocks.blocknames.length];
			for(int i = 0; i < stacks.length; i++){
				stacks[i] = new ItemStack(FAMMBlocks.get(FAMMBlocks.blocknames[i] + (!FAMM.conf3 ? "" : "_hb")));
			}
		}
	}

	@Override
	protected void init(){
		for(int i = 0; i < stacks.length; i++){
			int j = i % 10, k = i / 10;
			buttons.put("button" + i, new BasicButton("button" + i, guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18), 8, 22, 16, 16, true));
		}
	}

	@Override
	protected void predraw(float pticks, int mouseX, int mouseY){
		//
	}

	@Override
	protected void drawbackground(float pticks, int mouseX, int mouseY){
		GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
		for(int i = 0; i < stacks.length; i++){
			int j = i % 10, k = i / 10;
			this.itemRender.renderItemIntoGUI(stacks[i], guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18));
		}
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
		this.mc.getTextureManager().bindTexture(texloc);
	}

	@Override
	protected boolean buttonClicked(int mouseX, int mouseY, int mouseButton, String key, BasicButton button){
		if(button.name.startsWith("button")){
			int i = Integer.parseInt(button.name.replace("button", ""));
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("block", FAMMBlocks.blocknames[i]);
			container.send(Side.SERVER, compound);
			player.closeScreen();
		}
		return false;
	}

	@Override
	protected void scrollwheel(int am, int x, int y){
		//
	}

}
