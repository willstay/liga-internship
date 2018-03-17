package ru.liga.songtask.printer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrinterTests {
    private String text = "text";
    private String filePath = "test.txt";
    private Print print = new PrintToFile(filePath);
    @Test
    public void createFileTest(){
        print.print(text);
        File file = new File(filePath);
        assertThat(file.exists()).isEqualTo(true);
    }
    @Test
    public void textOfFileTest(){
        Path path = Paths.get(filePath);
        List<String> testList = new ArrayList<>();
        try {
            testList = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(testList.get(0)).isEqualTo(text);
    }
}
