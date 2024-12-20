package org.example.basics.homework;

import org.springframework.stereotype.Component;

@Component
public class ResultAnalyzer {
    public Result parsingResult(String[] resultArray){
        if (resultArray.length >= 4) {
            return new Result(resultArray[0],resultArray[1],resultArray[2],resultArray[3]);
        }
        else {
            return null;
        }
    }
}
