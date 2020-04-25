package net.fexcraft.mod.famm.blocks;

import net.fexcraft.lib.mc.registry.FCLRegistry;
import net.fexcraft.mod.famm.FAMM;
import net.fexcraft.mod.famm.items.FAMMItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FAMMBLK extends Block {
	
    public FAMMBLK(String name){
        super(Material.WOOD);
		setCreativeTab(FAMM.tabFAMM);
        this.setHarvestLevel("axe", 1);
        this.setHardness(1.0F);
        this.setResistance(10.0F);
        FCLRegistry.getAutoRegistry(FAMM.MODID).addBlock(name, this, null, 0, null);
    }
    
    @Override
    public boolean onBlockActivated(World w, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
    	if(!w.isRemote && hand == EnumHand.MAIN_HAND){
    		if(p.getHeldItemMainhand().getItem() == FAMMItems.ink){
    			p.openGui(FAMM.INSTANCE, 0, w, pos.getX(), pos.getY(), pos.getZ());
    		}
    	}
		return true;
    }
	
}
