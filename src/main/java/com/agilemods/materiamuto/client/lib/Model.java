package com.agilemods.materiamuto.client.lib;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public enum Model {

    ALCHEMICAL_CHESET("chest"),
    COLLECTOR("collector"),
    CONDENSER("condenser"),
    RELAY("relay"),
    MATTER_FURNACE("furnace"),
    TRANSMUTATION_TABLET("tablet");

    private IModelCustom model;

    private Model(String file) {
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation("materiamuto:models/" + file + ".tcn"));
    }

    public void renderAll() {
        model.renderAll();
    }

    public void renderOnly(String... groupNames) {
        model.renderOnly(groupNames);
    }

    public void renderPart(String partName) {
        model.renderPart(partName);
    }

    public void renderAllExcept(String... excludedGroupNames) {
        model.renderAllExcept(excludedGroupNames);
    }
}
