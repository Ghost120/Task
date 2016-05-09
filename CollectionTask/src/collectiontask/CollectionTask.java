/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontask;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sun.dc.pr.PathStroker;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class CollectionTask {
    //private List<String> list;

    public static void main(String[] args) throws IOException {
        //task1();
        task2("C:\\Users\\asus 123\\Documents\\NetBeansProjects");

    }

    /**
     * Возвращает Arraylist из файла
     */
    private static List<String> readFileToList(String fileName) {
        List<String> list = new ArrayList<>();
        try {

            Path path = Paths.get(fileName);
            list = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * Записывает List в файл
     *
     * @param list
     * @param fileName
     * @throws IOException
     */
    private static void writeListInFile(List<String> list, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        Files.write(path, list);
    }

    private static void task1() throws IOException {
        System.out.println("1. Ввести строки из файла, записать в список." + "\n"
                + " Вывести строки в файл в обратном порядке.");
        String fileName = "src\\file.txt";
        List<String> list = readFileToList(fileName);
        System.out.println(list);
        Collections.reverse(list);
        writeListInFile(list, fileName);
        System.out.println(list);

    }
/**
 * 
 * @param fileDirrectory -путь к дирректории
 * 
 */
    private static void task2(String fileDirrectory) {
        //String fileName = "C:\\Users\\asus 123\\Documents\\NetBeansProjects";
        Path pathSource = Paths.get(fileDirrectory);
        List<Path> katalPodkatal = new ArrayList<>();
        
        class Visitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                katalPodkatal.add(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                katalPodkatal.add(dir);
                return super.preVisitDirectory(dir, attrs);
            }
        }

        try {
            Files.walkFileTree(pathSource, new Visitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(katalPodkatal.toString());
    }


}
