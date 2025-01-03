package org.example.basics.test;

import org.example.basics.config.ConfigMain;
import org.example.basics.homework.Result;
import org.example.basics.homework.ResultsProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigMain.class);

        ResultsProcessor resultsProcessor = (ResultsProcessor) applicationContext.getBean(ResultsProcessor.class);

        resultsProcessor.readAllLines(new ClassPathResource("runningresults.csv").getFile().toPath());

        List<Result> b = resultsProcessor.getFastest("Ж","5 км", 5);
        for (Result c : b) {
            System.out.println(c);
        }
    }
}