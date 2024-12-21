package org.example.basics.homework;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResultsProcessor {
    private final ResultAnalyzer resultAnalyzer;
    private List<Result> resultList = new ArrayList<>();

    public ResultsProcessor(ResultAnalyzer resultAnalyzer) {
        this.resultAnalyzer = resultAnalyzer;
    }

    public void readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                this.resultList = csvReader.readAll().
                        stream().map(resultAnalyzer::parsingResult).
                        collect(Collectors.toList());
            }
        }
    }

    // Требуется для модульного тестирования
    public List<Result> getResultList() {
        return new ArrayList<>(resultList);
    }

    // Требуется для модульного тестирования
    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public List<Result> getFastest(String gender, String distance, int limit) {
        return this.resultList
                .stream()
                .filter(o -> o.gender().equals(gender) && o.distance().equals(distance))
                .sorted()
                .limit(limit)
                .toList();
    }
}
