package com.example;

import com.example.modules.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {

    private static List<Module> MODULES;

    @Autowired
    public App(List<Module> modules) {
        App.MODULES = modules;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Введите имя файла: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Module module = null;

        for (Module m : MODULES) {
            if(m.canWorkWithFile(input)){
                module = m;
            }
        }

        if(module != null){
            module.getFunctionsDescription();

            while (true) {
                System.out.println("Выберите одну предложенных функций: ");
                String function = scanner.nextLine();

                switch (function) {
                    case "1":
                        module.firstFunction(input);
                        return;
                        case "2":
                            module.secondFunction(input);
                            return;
                            case "3":
                                module.thirdFunction(input);
                                return;
                    default:
                        System.out.println("Функция введена неправильно!");
                        break;
                }
            }
        } else {
            System.out.println("Выбранный формат файлов не поддерживается!");
        }
    }
}