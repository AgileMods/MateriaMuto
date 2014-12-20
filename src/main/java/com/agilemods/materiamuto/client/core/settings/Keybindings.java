package com.agilemods.materiamuto.client.core.settings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import com.agilemods.materiamuto.common.lib.LibMisc;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static KeyBinding charge = new KeyBinding(LibMisc.CHARGE, Keyboard.KEY_V, LibMisc.CATEGORY);
    public static KeyBinding mode = new KeyBinding(LibMisc.MODE, Keyboard.KEY_M, LibMisc.CATEGORY);
    public static KeyBinding release = new KeyBinding(LibMisc.RELEASE, Keyboard.KEY_R, LibMisc.CATEGORY);
    public static KeyBinding toggle = new KeyBinding(LibMisc.TOGGLE, Keyboard.KEY_G, LibMisc.CATEGORY);
}
