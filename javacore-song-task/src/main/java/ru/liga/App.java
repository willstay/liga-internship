package ru.liga;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.liga.songtask.domain.DoAnalyze;
import ru.liga.songtask.domain.PrintToConsole;
import ru.liga.songtask.domain.PrintToFile;

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
@Slf4j
@EqualsAndHashCode
public class App {
    public static void main(String[] args) {
        PrintToFile printToFile = new PrintToFile("output.txt", DoAnalyze.analyze());
        printToFile.print();

        PrintToConsole printToConsole = new PrintToConsole(DoAnalyze.analyze());
        printToConsole.print();

        log.info(DoAnalyze.analyze());

    }
}
