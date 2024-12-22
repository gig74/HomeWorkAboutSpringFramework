package org.example.basics.homework;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ResultAnalyzer {
    public Result parsingResult(String[] resultArray)  {
        try {
            return new Result(resultArray[0], resultArray[1], resultArray[2], resultArray[3]);
        } catch (Exception e) {
            throw new RuntimeException("Не смог разобрать массив результата " + Arrays.toString(resultArray) + "\n" +e.getMessage() );
        }
    }
}
