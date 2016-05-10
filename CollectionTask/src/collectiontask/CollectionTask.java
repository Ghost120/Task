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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class CollectionTask {
    //private List<String> list;

    public static void main(String[] args) throws IOException {
        //task1();
        //List<Path> listKatal = task2("C:\\Users\\asus 123\\Documents\\NetBeansProjects");
        //task3();
        //task4();
        task5();

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

    /**
     * 1. Ввести строки из файла, записать в список. Вывести строки в файл в
     * обратном порядке.
     *
     * @throws IOException
     */
    private static void task1() throws IOException {
        String fileName = "src\\file.txt";
        List<String> list = readFileToList(fileName);
        System.out.println(list);
        Collections.reverse(list);
        writeListInFile(list, fileName);
        System.out.println(list);

    }

    /**
     * 2. Создать список из элементов каталога и его подкаталогов.
     *
     * @param fileDirrectory -путь к дирректории
     *
     */
    private static List<Path> task2(String fileDirrectory) {
        //System.out.println("2. Создать список из элементов каталога и его подкаталогов.");
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
        //System.out.println(katalPodkatal.toString());
        return katalPodkatal;
    }

    /**
     * 3. Занести стихотворения одного автора в список. Провести сортировку по
     * возрастанию длин строк.
     */
    private static void task3() {
        List<String> list = readFileToList("src\\file1.txt");
        list.forEach((s) -> System.out.println(s));
        Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
        list.forEach((s) -> System.out.print(s + "\n"));
    }

    /**
     * 4. Определить множество на основе множества целых чисел. Создать методы
     * для определения пересечения и объединения множеств.
     */
    private static void task4() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        System.out.println("set1: "+ set1.toString()+"\nset2: "+set2.toString());
        Set<Integer> union = SetOfInteger.concatSet(set1, set2);
        Set<Integer> intersect = SetOfInteger.isIntersect(set1, set2);
        System.out.println("union: " + union + "\nintersect:" + intersect);
    }
    
    
    /**
     * 5. Списки I(1..n) и U(1..n) содержат результаты n-измерений тока и 
     * напряжения на неизвестном сопротивлении R. Найти приближенное число R 
     * методом наименьших квадратов.
     */
    private static void task5(){
    
    }

}
