package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Directory directory = new Directory("Документы");
        directory.addFile("report.txt", 1200);
        directory.addFile("notes.txt", 500);
        directory.addFile("photo.jpg", 3500);

        System.out.println("Файлы директории " + directory.getName() + ":");
        for (Directory.FileInfo file : directory.getFiles()) {
            System.out.println(file);
        }

        List<Directory.FileInfo> sortedFiles = sortFilesBySize(directory);

        System.out.println("\nФайлы по возрастанию размера:");
        for (Directory.FileInfo file : sortedFiles) {
            System.out.println(file);
        }
    }

    public static List<Directory.FileInfo> sortFilesBySize(Directory directory) {
        List<Directory.FileInfo> sortedFiles = new ArrayList<>(directory.getFiles());
        sortedFiles.sort(new Comparator<Directory.FileInfo>() {
            @Override
            public int compare(Directory.FileInfo first, Directory.FileInfo second) {
                return Long.compare(first.getSize(), second.getSize());
            }
        });

        return sortedFiles;
    }
}
