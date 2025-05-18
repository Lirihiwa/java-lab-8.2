package com.example.modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Audio implements Module{

    String PATH = "D:\\FFL";

    @Override
    public boolean canWorkWithFile(String fileName) {
        return fileName.endsWith(".mp3");
    }

    @Override
    public void getFunctionsDescription() {
        System.out.println("Функции для работы с MP3");
        System.out.println("1. Вывод названия трека из тегов");
        System.out.println("2. Вывод длительности в секундах");
        System.out.println("3. Вывод битрейта трека");
    }

    @Override
    public void firstFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            AudioFile audioFile = AudioFileIO.read(new File(path));
            System.out.printf("Название трека: %s\n", audioFile.getTag().getFirst(FieldKey.TITLE));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void secondFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            AudioFile audioFile = AudioFileIO.read(new File(path));
            System.out.printf("Длительность трека: %d\n", audioFile.getAudioHeader().getTrackLength());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void thirdFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            AudioFile audioFile = AudioFileIO.read(new File(path));
            System.out.printf("Битрейт трека: %s\n", audioFile.getAudioHeader().getBitRate());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
