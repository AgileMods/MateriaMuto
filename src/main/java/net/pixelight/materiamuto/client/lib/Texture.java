package net.pixelight.materiamuto.client.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * @author dmillerw
 */
public enum Texture {

    ALCHEMICAL_CHEST("alchemicalChest"),
    ALCHEMICAL_CHEST_OVERLAY("alchemicalChest_overlay"),

    COLLECTOR_MK1("collector_mk1"),
    COLLECTOR_Mk2("collector_mk2"),
    COLLECTOR_MK3("collector_mk3"),
    COLLECTOR_OVERLAY("collector_overlay"),

    RELAY_MK1("relay_mk1"),
    RELAY_Mk2("relay_mk2"),
    RELAY_MK3("relay_mk3"),
    RELAY_OVERLAY("relay_overlay"),

    CONDENSER("condenser"),
    CONDENSER_OVERLAY("condenser_overlay"),

    MATTER_FURNACE_DARK_OFF("furnaceDark_off"),
    MATTER_FURNACE_DARK_ON("furnaceDark_on"),
    MATTER_FURNACE_RED_OFF("furnaceRed_off"),
    MATTER_FURNACE_RED_ON("furnaceRed_on"),
    MATTER_FURNACE_OVERLAY("furnace_overlay"),

    TRANSMUTATION_TABLET("tablet"),
    TRANSMUTATION_TABLET_OVERLAY("tablet_overlay");

    private final ResourceLocation texture;

    private Texture(String file) {
        this.texture = new ResourceLocation("materiamuto:textures/models/" + file + ".png");
    }

    public void bindTexture() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    }
}
