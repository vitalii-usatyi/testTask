package main.java.application;

import main.java.application.service.WordsCounter;

import java.io.IOException;

/**
 * Enter your text in "text.txt" file
 */
public class WordsCounterApp {

    public static void main(String[] args) throws IOException {
        new WordsCounter().run();
    }
}
