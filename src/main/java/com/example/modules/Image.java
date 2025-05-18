package com.example.modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class Image implements Module{

    String PATH = "D:\\FFL";

    @Override
    public boolean canWorkWithFile(String fileName) {
        return false;
    }

    @Override
    public void getFunctionsDescription() {
        System.out.println("Функции для работы с jpg");
        System.out.println("1. Вывод размера изображения");
        System.out.println("2. Вывод информации exif");
        System.out.println("3. Вывод автора изображения");
    }

    @Override
    public void firstFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            BufferedImage image = ImageIO.read(new File(path));

            System.out.println("Размеры изображения");
            System.out.printf("Ширина = %d\n", image.getWidth());
            System.out.printf("Высота = %d\n", image.getHeight());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void secondFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    System.out.printf("%s, %s\n", tag.getTagName(), tag.getDescription());
                }
            } else {
                System.out.println("Не найдено информации");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void thirdFunction(String filename) {
        String path = PATH + '\\' + filename;

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory exif = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exif != null) {
                System.out.printf("Автор изображения: %s\n", exif.getDescription(ExifSubIFDDirectory.TAG_ARTIST));
            }
            else {
                System.out.println("Информации об авторе нет");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
