package ru.liga;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.midichanger.Transpose;
import ru.liga.songtask.printer.Print;
import ru.liga.songtask.printer.PrintToFile;

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
        if(args[1].equals("analyze")){
            if(args.length == 2){
                log.info(DoAnalyze.analyze(args[0]));
                return;
            }
            if(args.length == 3 & args[2].equals("-f")){
                Print printToFile = new PrintToFile(args[0] + ".txt");
                printToFile.print(DoAnalyze.analyze(args[0]));
                return;
            }
        }
        if(args[1].equals("change") && args[2].equals("-trans") && args[4].equals("-tempo")){
            String outputName = args[0].substring(0,args[0].length() - 4);
            outputName += args[2].toString() + args[3].toString() + args[4].toString() + args[5].toString() + ".mid";

            Transpose transpose = new Transpose(args[0]);
            transpose.changeTone(Integer.valueOf(args[3]));
            transpose.changeTempo(Integer.valueOf(args[5]));
            transpose.toFile(outputName);
        }
    }
}
