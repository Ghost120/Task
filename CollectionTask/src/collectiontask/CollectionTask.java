/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontask;

import collectiontask.graph.Graph;
import collectiontask.task14.MyStructureList;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.*;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class CollectionTask {

    //private List<String> list;
    private static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //task1();
        //List<Path> listKatal = task2("C:\\Users\\asus 123\\Documents\\NetBeansProjects");
        //task3();
        //task4();
        //task5();//доделать
        //task6();
        //task7();
        //System.out.println(task8("(({)})"));
        //task9();
        //task10();
        //task11(100000);
        //task12();//доделать
        //task13();
        //task14();
        //task15();
        task16();

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
        System.out.println("set1: " + set1.toString() + "\nset2: " + set2.toString());
        Set<Integer> union = SetOfInteger.concatSet(set1, set2);
        Set<Integer> intersect = SetOfInteger.isIntersect(set1, set2);
        System.out.println("union: " + union + "\nintersect:" + intersect);
    }

    /**
     * 5. Списки I(1..n) и U(1..n) содержат результаты n-измерений тока и
     * напряжения на неизвестном сопротивлении R. Найти приближенное число R
     * методом наименьших квадратов.
     */
    private static void task5() {

    }

    /**
     * 6. Сложить два многочлена, если коэффициенты многочленов хранятся в
     * объекте HashMap (степень элемента многочлена – это ключ, коэффициент
     * элемента многочлена – это значение).
     */
    private static void task6() {
        Map<Integer, Integer> polynominal1;
        Map<Integer, Integer> polynominal2;
        Map<Integer, Integer> sum;
        System.out.print("Ведите степень многочлена:");
        int power = scn.nextInt();
        polynominal1 = initPolynomial(power);
        printPolynomial(polynominal1);
        polynominal2 = initPolynomial(power);
        printPolynomial(polynominal2);
        scn.close();
        sum = getSum(polynominal1, polynominal2);
        System.out.println("sum: ");
        printPolynomial(sum);
    }

    private static Map<Integer, Integer> getSum(Map<Integer, Integer> first, Map<Integer, Integer> second) {
        Map<Integer, Integer> map = new HashMap<>();
        int n;
        if ((first.size() - second.size()) < 0) {
            n = first.size();
        } else {
            n = second.size();
        }
        for (int i = 1; i <= n; i++) {
            map.put(i, first.get(i) + second.get(i));
        }

        return map;
    }

    /**
     *
     * @param power- степень многочлена
     * @return многочлен HashMap
     */
    private static Map<Integer, Integer> initPolynomial(int power) {
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("Введите коэффициенты многочлена: ");
        for (int i = 1; i <= power; i++) {
            System.out.print("Введите коэффициент " + (i) + ": ");
            map.put(i, scn.nextInt());
        }
        return map;
    }

    private static void printPolynomial(Map<Integer, Integer> map) {
        for (int i = map.size(); i >= 1; i--) {
            if (i > 1) {
                System.out.print(map.get(i) + "x^" + i + " + ");
            } else {
                System.out.print(map.get(i) + "x");
            }
        }
        System.out.println();
    }

    /**
     * 7. Умножить два многочлена заданной степени, если коэффициенты
     * многочленов хранятся в различных списках.
     */
    private static void task7() {
        System.out.print("Ведите степень многочлена:");
        int power = scn.nextInt();
        List<Integer> list1 = initPolynomialList(power);
        printPolynomialList(list1);
        List<Integer> list2 = initPolynomialList(power);
        printPolynomialList(list2);
        List<Integer> summa = new ArrayList<>();

        for (int i = 0; i < power; i++) {
            summa.add(list1.get(i) * list2.get(i));
        }
        System.out.println("summa: ");
        printPolynomialList(summa);
    }

    private static void printPolynomialList(List<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {

            if (i > 0) {
                System.out.print(list.get(i) + "x^" + (i + 1) + " + ");
            } else {
                System.out.print(list.get(i) + "x");
            }
        }
        System.out.println();
    }

    private static List<Integer> initPolynomialList(int power) {
        List<Integer> list = new ArrayList<>();
        System.out.println("Введите коэффициенты многочлена: ");
        for (int i = 0; i < power; i++) {
            System.out.print("Введите коэффициент " + (i + 1) + ": ");
            list.add(scn.nextInt());
        }
        return list;
    }

    /**
     * 8. Задана строка, содержащая символы '(', ')', '[', ']', '{', '}'.
     * Проверить правильность расстановки скобок. Использовать стек.
     */
    private static boolean task8(String str) {
        Stack<Character> stack = new Stack<>();
        char[] strChar = str.toCharArray();

        for (char c : strChar) {
            if (isOpenBracket(c)) {
                stack.push(c);
                continue;
            }

            if (isCloseBracket(c)) {

                if (stack.isEmpty()) {

                    return false;
                }

                if (c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
                if (c == '}') {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
                if (c == ']') {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }

            }

        }

        return true;
    }

    private static boolean isOpenBracket(char c) {
        return ((c == '(') || (c == '{') || (c == '['));
    }

    private static boolean isCloseBracket(char c) {
        return ((c == ')') || (c == '}') || (c == ']'));
    }

    /**
     * 9. Задан файл с текстом на английском языке. Выделить все различные
     * слова. Слова, отличающиеся только регистром букв, считать одинаковыми.
     * Использовать класс HashSet.
     */
    private static void task9() {
        Set<String> set = new HashSet<>();
        try (Stream<String> stream = Files.lines(Paths.get("src\\task9.txt"))) {
            set = stream.map(String::toLowerCase)
                    .map(s -> s.replaceAll("[^ A-Za-z0-9]", ""))
                    .flatMap(Pattern.compile(" ")::splitAsStream)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        set.forEach(System.out::println);

    }

    /**
     * 10. Задан файл с текстом на английском языке. Выделить все различные
     * слова. Для каждого слова подсчитать частоту его встречаемости. Слова,
     * отличающиеся регистром букв, считать различными. Использовать класс
     * HashMap.
     */
    private static void task10() {
        Map<String, Integer> map = new HashMap();
        try (Stream<String> stream = Files.lines(Paths.get("src\\task9.txt"))) {
            String[] temp;
            temp = stream
                    .map(s -> s.toLowerCase())
                    .flatMap(Pattern.compile(" ")::splitAsStream)
                    .toArray(String[]::new);
            for (String s : temp) {
                if (s.equals("")) {
                    continue;
                }
                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + 1);
                } else {
                    map.put(s, 1);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        map.forEach((key, val) -> System.out.println("слово: " + key + " встречается: " + val + " раз"));
    }

    /**
     * *
     * 11. В кругу стоят N человек, пронумерованных от 1 до N. При ведении счета
     * по кругу вычеркивается каждый второй человек, пока не останется один.
     * Составить две программы, моделирующие процесс. Одна из программ должна
     * использовать класс ArrayList, а вторая – LinkedList. Какая из двух
     * программ работает быстрее? Почему?
     *
     * @param n -количество человек в круге
     */
    private static void task11(int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long time = System.nanoTime();
        removeEverySecondInter(arrayList);
        System.out.println("time ArrayList: " + ((System.nanoTime() - time) / 1_000_000) + " мс");
        time = System.nanoTime();
        removeEverySecondInter(linkedList);
        System.out.println("time LinkedList: " + ((System.nanoTime() - time) / 1_000_000) + " мс");
    }

    /**
     * метод удаляем каждый второй элемент по кругу
     *
     * @param list
     */
    private static void removeEverySecondInter(List list) {
        int firstdel = 0;
        while (list.size() > 1) {
            if (firstdel == 0) {
                for (int i = 1; i < list.size(); i++) {
                    list.remove(i);
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    list.remove(i);
                }
            }
            firstdel = list.size() % 2 == 0 ? 0 : 1;
        }
        System.out.println("остался: " + list.toString());
    }

    /**
     * 12. Задан список целых чисел и число X. Не используя вспомогательных
     * объектов и не изменяя размера списка, переставить элементы списка так,
     * чтобы сначала шли числа, не превосходящие X, а затем числа, большие X.
     */
    private static void task12() {
        System.out.println("Введите число Х");
        int x = scn.nextInt();
        System.out.println("Введите размер списка");
        int n = scn.nextInt();
        scn.close();
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(rnd.nextInt(n));
        }
        //Collections.sort(list);
        System.out.println(list.toString());
        int tmp = 0;
        int mem;
        for (int i = 0; i < n; i++) {
            if (list.get(i) < x) {
                mem = list.get(i);
                //list.add(tmp, list.get());
                list.add(i, list.get(mem));
                // list.add(i, mem);
            } else {
                tmp = i;
            }
        }
        System.out.println(list.toString());

    }

    /**
     * 13. Реализовать класс Graph, представляющий собой неориентированный граф.
     * В конструкторе класса передается количество вершин в графе. Методы должны
     * поддерживать быстрое добавление и удаление ребер.
     */
    private static void task13() {
        Graph graph = new Graph(10);
        graph.addSide(12, 123);
        graph.addSide(34, 45);
        System.out.println(graph.getVertexes().toString());
    }

    /**
     * 14. На базе коллекций реализовать структуру хранения чисел с поддержкой
     * следующих операций: добавление/удаление числа; поиск числа, наиболее
     * близкого к заданному (т.е. модуль разницы минимален).
     */
    private static void task14() {
        MyStructureList myList = new MyStructureList();
        myList.addNumber(3);
        myList.addNumber(5);
        myList.addNumber(7);
        myList.addNumber(8);
        myList.addNumber(19);
        myList.addNumber(45);
        System.out.println("sd= наиболее близкое к заданному числу :" + myList.searchNumber(22));
        System.out.println(myList.getList());
        myList.deleteNumber(3);
        System.out.println(myList.getList());

    }

    /**
     * 15. На плоскости задано N точек. Вывести в файл описания всех прямых,
     * которые проходят более чем через 2 точки из заданных. Для каждой прямой
     * указать, через сколько точек она проходит. Использовать класс HashMap.
     */
    private static void task15() {
        Map<Line2D, Integer> map;

        System.out.print("Введите количество точек: ");
        int n = scn.nextInt();
        scn.close();
        List<Point> points = getListPoints(n);
        //System.out.println(points.toString());
        map = getThisLineMap(points);
        // System.out.println(points.toString());

    }

    private static List<Point> getListPoints(int n) {
        Random rnd = new Random();
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Point(rnd.nextInt(10), rnd.nextInt(10)));
        }
        return list;
    }

    /**
     * возвращает коллекцию прямых, которые проходят более чем через 2 точки из
     * заданных. Для каждой прямой указано, через сколько точек она проходит.
     *
     * @param pointList список точек
     * @return коллекцию линий с количеством
     */
    private static Map<Line2D, Integer> getThisLineMap(List<Point> pointList) {
        Map<Line2D, Integer> map = new HashMap<>();
        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i; j < pointList.size(); j++) {

                Point p1 = pointList.get(i);
                Point p2 = pointList.get(j);

                if (p1.equals(p2)) {
                    continue;
                }

                Line2D.Double line = new Line2D.Double(p1, p2);
                map.put(line, 2);
            }
        }
        Map<Line2D, Integer> resultMap = new HashMap<>();

        map.forEach((line, value) -> {
            pointList.forEach(point -> {
                if (!line.getP1().equals(point) && !line.getP2().equals(point)) {
                    if (intersects(line, point)) {
                        System.out.println(line.getP1().getX() + " " + line.getP1().getY()
                                + " " + line.getP2().getX() + " " + line.getP2().getY()
                                + " пересекается с " + point.getX() + " " + point.getY());
                        if (!resultMap.containsKey(line)) {
                            resultMap.put(line, value + 1);
                        } else {
                            resultMap.replace(line, resultMap.get(line) + 1);
                        }
                    }
                }
            });
        });

        return resultMap;
    }

    /**
     * пересекается ли линия с точкой
     *
     * @param line2D линия
     * @param p точка
     * @return пересекается ли линия с точкой
     */
    private static boolean intersects(Line2D line2D, Point p) {
        return line2D.ptLineDist(p) <= 0.01;
    }

    /**
     * 16. На клетчатой бумаге нарисован круг. Вывести в файл описания всех
     * клеток, целиком лежащих внутри круга, в порядке возрастания расстояния от
     * клетки до центра круга. Использовать класс PriorityQueue.
     */
    private static void task16() {
        System.out.print("Введите центр окружности\nX: ");
        double x = scn.nextDouble();
        System.out.print("Y: ");
        double y = scn.nextDouble();
        System.out.print("Введите радиус окружности\nR: ");
        double radius = scn.nextDouble();
        scn.close();
        for (int i = 0; i < 4; i++) {

        }
    }

    private static void getLength2Point(Point point1,Point point2) {
        double a= Math.pow((point2.x -point1.x),2);
    }

}
