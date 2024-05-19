package ca.worldwidepixel.sulfur.shader;

import ca.worldwidepixel.sulfur.Sulfur;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SuperSecret implements ClientModInitializer {
    private static final ManagedShaderEffect FLIP = ShaderEffectManager.getInstance()
            .manage(new Identifier("minecraft", "shaders/post/flip.json"));
    private static boolean enabled = false;

    static int currentShader = 0;

    static List<ManagedShaderEffect> shaderList;

    public void onInitializeClient() {

        List<String> shaderFiles = List.of("shaders/post/antialias.json", "shaders/post/art.json",
                "shaders/post/bits.json", "shaders/post/blobs.json", "shaders/post/blobs2.json",
                "shaders/post/blur.json", "shaders/post/bumpy.json", "shaders/post/color_convolve.json",
                "shaders/post/creeper.json", "shaders/post/deconverge.json", "shaders/post/desaturate.json",
                "shaders/post/flip.json", "shaders/post/fxaa.json", "shaders/post/green.json",
                "shaders/post/invert.json", "shaders/post/notch.json", "shaders/post/ntsc.json",
                "shaders/post/outline.json", "shaders/post/pencil.json", "shaders/post/phosphor.json",
                "shaders/post/scan_pincushion.json", "shaders/post/sobel.json", "shaders/post/spider.json",
                "shaders/post/wobble.json");

        shaderList = new ArrayList<ManagedShaderEffect>();

        for (String shader : shaderFiles) {
            final ManagedShaderEffect shaderFromList = ShaderEffectManager.getInstance()
                    .manage(new Identifier(shader));
            shaderList.add(shaderFromList);
        }

        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (enabled) {
                shaderList.get(currentShader).render(tickDelta);
            }
        });
    }

    public static void changeShader() {
        if (currentShader == 0 && !enabled) {
            enabled = true;
        } else if (shaderList.size() - 1 == currentShader && enabled) {
            enabled = false;
        } else if (shaderList.size() - 1 == currentShader) {
            enabled = true;
            currentShader = 0;
        } else {
            currentShader++;
        }
    }

}
