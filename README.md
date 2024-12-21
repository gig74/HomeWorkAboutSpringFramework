# HomeWorkAboutSpringFramework
 Домашнее задание по теме "Что такое Spring framework и зачем он нужен?" (ProductStar)

# Постановка задачи
Реализовать Java-приложение с использованием Spring Framework для обработки результатов соревнований по легкой атлетике

## Требования
- На вход приложение получает csv-файл с результатами в формате: Фамилия Имя, Пол, Дистанция, Время (Пример – Иван Иванов, М, 10 км, 55:20).
- Spring Application Context должен содержать bean с именем ResultsProcessor с методом для загрузки результатов и для получения N самых быстрых бегунов в зависимости от переданных аргументов – дистанция (5 или 10 км), пол (М или Ж).
- Все public методы классов должны быть покрыты unit-тестами

## Подключенные зависимости и плагины
- spring-context -  предоставляет доступ к сконфигурированным объектам, таким как контекст
- opencsv - библиотека для работы с csv
- junit-jupiter-api - библиотека для unit-тестов

## Описание основных файлов
- /config/ConfigMain.java - конфигурация для spring
- homework/Result.java - класс объекта строки результата соревнований 
- homework/ResultAnalyzer - класс для метода преобразования массива строк в объект Result
- homework/ResultProcessor - класс для основных методов задания (загрузка из файла, фильтрация результатов и тд)
- test/AllTest - класс модульного тестирования всех созданных Public методов
- test/Main - исполняемый класс для примера использования и интеграционного тестирования всей функциональности
- resources/runningresults.csv - файл со входными данными для интеграционного тестирования 