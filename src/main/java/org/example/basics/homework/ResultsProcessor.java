package org.example.basics.homework;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.basics.homework.exceptions.BadArgumentException;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    public void readAllLines(Path filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(filePath))) {
            this.resultList = csvReader.readAll().
                    stream().map(resultAnalyzer::parsingResult).
                    collect(Collectors.toList());
        }  catch (IOException e) {
            throw new IOException("Не смог прочитать файл " + filePath + "\n" + e.getMessage());
        } catch (CsvException e) {
            throw new CsvException("Не смог разобрать данные из csv-файла " + filePath + "\n" + e.getMessage());
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

    public List<Result> getFastest(String gender, String distance, int limit) throws BadArgumentException {
        StringBuilder registerNullArgument = new StringBuilder();
        if (gender == null || gender.isEmpty()) {
            registerNullArgument.append("gender ");
        }
        if (distance == null || distance.isEmpty()) {
            registerNullArgument.append("distance ");
        }
        if (limit <= 0) {
            registerNullArgument.append("limit ");
        }
        if (!registerNullArgument.toString().isEmpty()) {
            throw new BadArgumentException("Неверно заполнены аргументы для обращения: " + registerNullArgument);
        }
        return this.resultList
                .stream()
                .filter(o -> o.gender().equalsIgnoreCase(gender) && o.distance().equalsIgnoreCase(distance))
                .sorted()
                .limit(limit)
                .toList();
    }
}
