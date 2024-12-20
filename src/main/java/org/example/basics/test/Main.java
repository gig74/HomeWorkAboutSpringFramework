package org.example.basics.test;

import org.example.basics.config.ConfigMain;
import org.example.basics.homework.ReadFileResult;
import org.example.basics.homework.Result;
import org.example.basics.homework.ResultAnalyzer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigMain.class);

        ReadFileResult readFileResult = (ReadFileResult) applicationContext.getBean(ReadFileResult.class);
        ResultAnalyzer resultAnalyzer = (ResultAnalyzer) applicationContext.getBean(ResultAnalyzer.class);
//        List<String[]> a = readAllLines(new ClassPathResource("results.csv").getFile().toPath());
        File a = new ClassPathResource("runningresults.csv").getFile();
        List<Result> b = readFileResult.readAllLines(new ClassPathResource("runningresults.csv").getFile().toPath()).
                stream().map(resultAnalyzer::parsingResult).
                sorted().
                filter( o -> o.gender().equals("Ж")).
                collect(Collectors.toList());
        for (Result c : b) {
            System.out.println(c);
        }
    }
}