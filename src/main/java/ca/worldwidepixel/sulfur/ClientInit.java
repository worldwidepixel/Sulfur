package ca.worldwidepixel.sulfur;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientInit implements ClientModInitializer {
    List<Integer> sortList1 = new ArrayList<>();
    List<Integer> sortList2 = new ArrayList<>();
    Random r = new Random();

    @Override
    public void onInitializeClient() {
        for (int i = 0; i < 2000; i++) {
            sortList1.add(r.nextInt());
        }
        for (int i = 0; i < 8000; i++) {
            sortList2.add(r.nextInt());
        }
        WorldRenderEvents.START.register((context -> {
            if (r.nextInt(120) == 1) BubbleSort.sort(sortList2);
            else BubbleSort.sort(sortList1);
        }));
    }
}
