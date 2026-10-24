package io.github.suel_ki.beautify.client.util;

//? if >=1.21.11
import net.minecraft.client.Minecraft;
//? if <1.21.11
//import net.minecraft.client.gui.screens.Screen;

public class ClientKeys {
    public static boolean hasControlDown() {
        //? if >=1.21.11
        return Minecraft.getInstance().hasControlDown();
        //? if <1.21.11
        //return Screen.hasControlDown();
    }

    public static boolean hasShiftDown() {
        //? if >=1.21.11
        return Minecraft.getInstance().hasShiftDown();
        //? if <1.21.11
        //return Screen.hasShiftDown();
    }
}
