package org.example.basics.test;

import org.example.basics.homework.Result;
import org.example.basics.homework.ResultAnalyzer;
import org.example.basics.homework.ResultsProcessor;
import org.example.basics.homework.exceptions.BadArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllTest {
    private ResultAnalyzer resultAnalyzer;
    private ResultsProcessor resultsProcessor;
    List<Result> resultListTest;
    @BeforeEach
    void setUp() {
        resultAnalyzer = new ResultAnalyzer();
        resultsProcessor = new ResultsProcessor(resultAnalyzer);
        resultListTest = new ArrayList<>();

        Result result_01 = new Result("Митрофанова Диана Кирилловна","Ж","10 км","36:32");
        Result result_02 = new Result("Королева Ксения Владимировна","Ж","5 км","19:58");
        Result result_03 = new Result("Иванова Милана Данииловна","Ж","10 км","36:08");
        Result result_04 = new Result("Гусев Александр Николаевич","М","10 км","30:58");
        Result result_05 = new Result("Попова Александра Максимовна","Ж","10 км","41:40");
        resultListTest.add(result_01);
        resultListTest.add(result_02);
        resultListTest.add(result_03);
        resultListTest.add(result_04);
        resultListTest.add(result_05);
    }
    @Test
    void parsingResultTest() {
        String[] stringsForParsing = new String[] {"Иванова Милана Данииловна","Ж","10 км","36:08"};
        Result result = new Result("Иванова Милана Данииловна","Ж","10 км","36:08");
        assertEquals(result, resultAnalyzer.parsingResult(stringsForParsing));

        String[] badStringsForParsing = new String[] {"Иванова Милана Данииловна","Ж","10 км"};
        assertThrows(RuntimeException.class, () -> resultAnalyzer.parsingResult(badStringsForParsing));
    }
    @Test
    void getResultListAndSetResultListTest() {
        resultsProcessor.setResultList(resultListTest);
        assertEquals(resultListTest, resultsProcessor.getResultList());
    }
    @Test
    void readAllLinesTest() throws Exception {
        resultsProcessor.readAllLines(new ClassPathResource("runningresults.csv").getFile().toPath());
        assertEquals(60, resultsProcessor.getResultList().size());
        assertThrows(IOException.class, () -> resultsProcessor.readAllLines(new ClassPathResource("сборка тестового csv.xlsx").getFile().toPath()));
    }
    @Test
    void getFastestTest() throws BadArgumentException {
        resultsProcessor.setResultList(resultListTest);
        List<Result> resultListControl = new ArrayList<>();
        Result result_03 = new Result("Иванова Милана Данииловна","Ж","10 км","36:08");
        Result result_01 = new Result("Митрофанова Диана Кирилловна","Ж","10 км","36:32");
        resultListControl.add(result_03);
        resultListControl.add(result_01);

        assertEquals(resultListControl, resultsProcessor.getFastest("Ж","10 км", 2));
        assertThrows(BadArgumentException.class, () -> resultsProcessor.getFastest("Ж","10 км", 0));
        assertThrows(BadArgumentException.class, () -> resultsProcessor.getFastest(null,"10 км", 2));
        assertThrows(BadArgumentException.class, () -> resultsProcessor.getFastest("Ж","", 2));
    }

}