package com.example.modules;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Folder implements Module{

    String PATH = "D:\\FFL";

    @Override
    public boolean canWorkWithFile(String fileName) {
        File f = new File(PATH + '\\' + fileName);
        return f.isDirectory();
    }

    @Override
    public void getFunctionsDescription() {
        System.out.println("Функции для работы с каталогами");
        System.out.println("1. Вывод списка файлов в каталоге");
        System.out.println("2. Подсчет размера всех файлов в каталоге");
        System.out.println("3. Процентное соотношение каталогов и файлов внутри каталога");
    }

    @Override
    public void firstFunction(String filename) {
        String path = PATH + '\\' + filename;

        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files != null) {
            System.out.println("Список файлов в каталоге");

            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    @Override
    public void secondFunction(String filename) {
        String path = PATH + '\\' + filename;

        File dir = new File(path);
        File[] files = dir.listFiles();

        long size = 0;

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                }
            }
        }

        System.out.printf("Размер файлов в каталоге: %d байт\n", size);
    }

    @Override
    public void thirdFunction(String filename) {
        String path = PATH + '\\' + filename;

        File dir = new File(path);
        File[] files = dir.listFiles();

        int directoriesCount = 0;
        int filesCount = 0;
        float dirPercentage = 0;
        float filePercentage = 0;

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filesCount++;
                } else if (file.isDirectory()) {
                    directoriesCount++;
                }
            }

            dirPercentage = directoriesCount == 0 ? directoriesCount : (float) directoriesCount / (float) files.length;
            filePercentage = filesCount == 0 ? filesCount : (float) filesCount / (float) files.length;
        }

        System.out.println("Процентное соотношение:");
        System.out.printf("Каталогов = %f %%\n", dirPercentage);
        System.out.printf("Файлов = %f %%\n", filePercentage);
    }
}
