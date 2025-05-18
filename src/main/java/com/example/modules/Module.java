package com.example.modules;

public interface Module {

    boolean canWorkWithFile(String fileName);
    void getFunctionsDescription();
    void firstFunction(String filename);
    void secondFunction(String filename);
    void thirdFunction(String filename);
}
