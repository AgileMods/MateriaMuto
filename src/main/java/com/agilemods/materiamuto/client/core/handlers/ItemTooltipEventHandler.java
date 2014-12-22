package com.agilemods.materiamuto.client.core.handlers;

import com.agilemods.materiamuto.api.wrapper.VanillaStackWrapper;
import com.agilemods.materiamuto.common.emc.EMCHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import com.agilemods.materiamuto.common.emc.EMCRegistry;

public class ItemTooltipEventHandler {

    @SubscribeEvent
    public void onRenderTooltip(ItemTooltipEvent event) {
        double emc = EMCRegistry.getEMC(new VanillaStackWrapper(event.itemStack).setNBT(event.itemStack.getTagCompound()));
        if (emc > 0) {
            event.toolTip.add("EMC: " + EMCHelper.emcToString(emc));
        }
    }
}
