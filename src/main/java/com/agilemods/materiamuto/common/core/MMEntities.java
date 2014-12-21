package com.agilemods.materiamuto.common.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import com.agilemods.materiamuto.MateriaMuto;
import com.agilemods.materiamuto.common.entity.EntityHomingArrow;
import com.agilemods.materiamuto.common.entity.EntityLootBall;
import com.agilemods.materiamuto.common.lib.LibEntityNames;

public class MMEntities {

    public static void init() {
        int id = 0;
        EntityRegistry.registerModEntity(EntityLootBall.class, LibEntityNames.LOOT_BALL, id++, MateriaMuto.instance, 64, 10, false);
        EntityRegistry.registerModEntity(EntityHomingArrow.class, LibEntityNames.HOMING_ARROW, id++, MateriaMuto.instance, 2048, 50, true);

    }
}
