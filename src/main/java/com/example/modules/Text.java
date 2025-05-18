package com.example.modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class Text implements Module{

    String PATH = "D:\\FFL";

    @Override
    public boolean canWorkWithFile(String fileName) {
        return fileName.endsWith(".txt");
    }

    @Override
    public void getFunctionsDescription() {
        System.out.println("Функции для работы с txt");
        System.out.println("1. Подсчет и вывод количества строк");
        System.out.println("2. Вывод частоты вхождения каждого символа");
        System.out.println("3. Вывод количества слов");
    }

    @Override
    public void firstFunction(String filename) {
        String path = PATH + '\\' + filename;

        int lines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((br.readLine()) != null){
                lines++;
            }

            System.out.printf("Количечтво строк = %s\n", lines);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void secondFunction(String filename) {
        String path = PATH + '\\' + filename;

        Map<Character, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()){
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                System.out.printf("%s: %d\n", pair.getKey(), pair.getValue());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void thirdFunction(String filename) {
        String path = PATH + '\\' + filename;

        String line;
        int wordsCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null){
                String[] words = line.split(" ");

                wordsCount += words.length;
            }

            System.out.printf("Количечтво слов = %d\n", wordsCount);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
