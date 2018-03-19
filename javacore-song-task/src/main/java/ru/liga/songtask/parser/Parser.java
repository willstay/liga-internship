package ru.liga.songtask.parser;

import lombok.extern.slf4j.Slf4j;
import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.midichanger.Transpose;
import ru.liga.songtask.printer.Print;
import ru.liga.songtask.printer.PrintToFile;
@Slf4j
public class Parser {
    public static void parse(String [] args){
        if (args.length == 0) {
            return;
        }

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
