package net.fexcraft.mod.famm.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.fexcraft.lib.mc.gui.GenericGui;
import net.fexcraft.mod.famm.FAMM;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;

public class ChangeGui extends GenericGui<ChangeContainer> {
	
	private static ArrayList<ItemStack> stacks;
	private static ArrayList<String> ids;
	private static BasicText scr;
	private static int scroll;

	public ChangeGui(EntityPlayer player, int x, int y, int z){
		super(new ResourceLocation("famm:textures/gui/change.png"), new ChangeContainer(player, x, y, z), player);
		xSize = 201;
		ySize = 154;
		if(stacks == null){
			stacks = new ArrayList<>();
			ids = new ArrayList<>();
			addEntries(FAMM.conf3 ? "_hb" : "");
		}
	}

	private void addEntries(String suffix){
		for(int i = 0; i < FAMMBlocks.all_blocknames.length; i++){
			String id = FAMMBlocks.all_blocknames[i] + suffix;
			Block block = FAMMBlocks.get(id);
			if(block != null){
				stacks.add(new ItemStack(block));
				ids.add(id);
			}
		}
	}

	@Override
	protected void init(){
		for(int i = 0; i < 70; i++){
			int j = i % 10, k = i / 10;
			buttons.put("button_" + ids.get(i), new BasicButton("button_" + i, guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18), 8, 22, 16, 16, true));
		}
		buttons.put("up", new BasicButton("up", guiLeft + 188, guiTop + 26, 188, 26, 7, 8, true));
		buttons.put("dw", new BasicButton("dw", guiLeft + 188, guiTop + 133, 188, 133, 7, 8, true));
		texts.put("page", scr = new BasicText(guiLeft + 162, guiTop + 9, 24, null, "-/-"));
	}

	@Override
	protected void predraw(float pticks, int mouseX, int mouseY){
		scr.string = "<" + scroll + ">";
	}

	@Override
	protected void drawbackground(float pticks, int mouseX, int mouseY){
		GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
		for(int i = 0; i < 70; i++){
			int j = i % 10, k = i / 10, l = i + (scroll * 10);
			if(l >= stacks.size()) break;
			this.itemRender.renderItemIntoGUI(stacks.get(l), guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18));
		}
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
		this.mc.getTextureManager().bindTexture(texloc);
	}

	@Override
	protected boolean buttonClicked(int mouseX, int mouseY, int mouseButton, String key, BasicButton button){
		if(button.name.startsWith("button_")){
			NBTTagCompound compound = new NBTTagCompound();
			Integer idx = Integer.parseInt(button.name.substring("button_".length()));
			if((idx += scroll * 10) >= stacks.size()) return true;
			compound.setString("block", ids.get(idx).replace("_hb", ""));
			container.send(Side.SERVER, compound);
			player.closeScreen();
		}
		else if(button.name.equals("up")){
			scroll--;
			if(scroll < 0) scroll = 0;
		}
		else if(button.name.equals("dw")){
			scroll++;
		}
		return false;
	}

	@Override
	protected void scrollwheel(int am, int x, int y){
		scroll += am > 0 ? 1 : -1;
		if(scroll < 0) scroll = 0;
	}

}
