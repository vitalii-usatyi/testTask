package main.java.application.service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordsCounter {

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = in.nextLine();

        List<String> words = getWords(text);

        LinkedHashMap<String, Long> result = countWords(words);

        result.forEach((word, frequency) -> System.out.println(word + " " + frequency));
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
            words[i] = words[i].replaceAll("[.,_!?;:]", "");
        }
        return List.of(words);
    }
}
