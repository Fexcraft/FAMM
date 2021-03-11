package net.fexcraft.mod.famm.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.fexcraft.lib.mc.gui.GenericGui;
import net.fexcraft.mod.famm.FAMM;
import net.fexcraft.mod.famm.blocks.FAMMBLK;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public class ChangeGui extends GenericGui<ChangeContainer> {
	
	private static ArrayList<ItemStack> stacks;
	private static ArrayList<String> ids;
	private static boolean full;

	public ChangeGui(EntityPlayer player, int x, int y, int z){
		super(new ResourceLocation("famm:textures/gui/change.png"), new ChangeContainer(player, x, y, z), player);
		full = player.world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof FAMMBLK;
		xSize = 194;
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
		for(int i = 0; i < stacks.size(); i++){
			int j = i % 10, k = i / 10;
			buttons.put("button_" + ids.get(i), new BasicButton("button_" + ids.get(i), guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18), 8, 22, 16, 16, true));
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
		for(int i = 0; i < stacks.size(); i++){
			int j = i % 10, k = i / 10;
			this.itemRender.renderItemIntoGUI(stacks.get(i), guiLeft + 8 + (j * 18), guiTop + 22 + (k * 18));
		}
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
		this.mc.getTextureManager().bindTexture(texloc);
	}

	@Override
	protected boolean buttonClicked(int mouseX, int mouseY, int mouseButton, String key, BasicButton button){
		if(button.name.startsWith("button_")){
			NBTTagCompound compound = new NBTTagCompound();
			String id = button.name.substring("button_".length());
			compound.setString("block", full ? id.replace("_hb", "") : id);
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
