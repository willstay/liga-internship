package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.Map;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        System.out.println("Всего нот: " + simpleMidiFile.vocalNoteList().size());
        //System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        System.out.println("<p>");
        System.out.println("Анализ диапазона:");
        System.out.println("верхняя: " + simpleMidiFile.getHighestNote().sign().fullName());
        System.out.println("нижняя: " + simpleMidiFile.getLowestNote().sign().fullName());
        System.out.println("диапазон: " + simpleMidiFile.getRangeNotes());
        System.out.println("<p>");
        System.out.println("Анализ длительности нот (мс):");

        for(Map.Entry<Long,Integer> duration : simpleMidiFile.analyzeDuration().entrySet()){
            System.out.println(duration.getKey() + ": " + duration.getValue());
        }

        System.out.println("<p>");
        System.out.println("Анализ нот по высоте:");

    }
}
