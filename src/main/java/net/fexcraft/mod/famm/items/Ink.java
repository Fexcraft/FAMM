package net.fexcraft.mod.famm.items;

import net.fexcraft.lib.mc.registry.FCLRegistry;
import net.fexcraft.mod.famm.FAMM;
import net.minecraft.item.Item;

public class Ink extends Item {
	
	public Ink(){
		setCreativeTab(FAMM.tabFAMM);
		FCLRegistry.getAutoRegistry("famm").addItem("ink", this, 0, null);
	}
	
}