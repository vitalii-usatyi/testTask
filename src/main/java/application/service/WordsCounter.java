package main.java.application.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordsCounter {

    public void run() throws IOException {
        String text = getTextFromFile();
        List<String> words = getWords(text);
        LinkedHashMap<String, Long> result = countWords(words);
        result.forEach((word, frequency) -> System.out.println(word + " " + frequency));
    }

    private String getTextFromFile() throws IOException {
        File file = new File("src/main/resources/text.txt");
        List<String> lines = Files.readAllLines(Path.of(file.getAbsolutePath()));
        return String.join("", lines);
    }

    private LinkedHashMap<String, Long> countWords(List<String> listOfWords) {
        Map<String, Long> wordFrequencyMap = listOfWords.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return sortByFrequency(wordFrequencyMap);
    }

    private LinkedHashMap<String, Long> sortByFrequency(Map<String, Long> wordFrequency) {
        return wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private List<String> getWords(String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[.,_!?;:\'\"]", "");
        }
        return List.of(words);
    }
}
