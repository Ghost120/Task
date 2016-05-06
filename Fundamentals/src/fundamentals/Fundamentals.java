package fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class Fundamentals {

    private static int n = 2;

    public static void main(String[] args) {
        task1();
        //task2();
        //task3();
        //task4();
        //task11();
        //task22();
        //task16();
        //task5();
        //task6();
        //task7();
        //task8();
        //task9();
        //task10();
        //task13();
        //task14();
        //task15();
        //task17();
        //task18();
        //task19();//доделать
        //task20();
        //task21();
        //task23();
        //task24();
        //task25();
        //task26();
        //task27();
    }

    private static String[] consoleReader() {
        String[] temp = new String[n];
        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Введите слово и нажмите Enter:  ");
            temp[i] = scn.nextLine();
        }
        scn.close();
        return temp;
    }

    private static int[][] getMatrix() {
        int[][] matr = new int[n][n];
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                Random random = new Random();
                matr[i][j] = -n + random.nextInt(2 * n + 1);
            }
        }
        return matr;
    }

    private static void printMatr(int[][] mass) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mass[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static void printMatrDouble(double[][] mass) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mass[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static void task13() {
        System.out.println("13. Выполнить циклический сдвиг заданной матрицы на k позиций вниз.");
        int matr[][] = getMatrix();
        printMatr(matr);
        System.out.print("Введите k для сдвига: ");
        Scanner scn = new Scanner(System.in);
        int k = scn.nextInt();
        int tmp[][] = new int[4][4];

        for (int i = 0; i < k; i++) {
            tmp[0] = matr[n - 1];
            System.arraycopy(matr, 0, matr, 1, matr.length - 2 + 1);
            matr[0] = tmp[0];
        }
        printMatr(matr);

    }

    private static void task3() {
        System.out.println("3. Ввести n строк с консоли. Вывести на консоль те строки, длина которых меньше средней, а также длину.");
        String[] temp = consoleReader();
        int average = 0;
        for (String temp1 : temp) {
            average += temp1.length();
        }
        average = average / n;

        for (String s : temp) {
            if (s.length() < average) {
                System.out.println(s + " длинна - " + s.length());
            }
        }
    }

    private static void task14() {
        System.out.println("14. Найти и вывести наибольшее число возрастающих элементов матрицы, идущих подряд.");
        int[][] matr = getMatrix();
        printMatr(matr);
        int[] maxVosr = new int[4];
        int max = 0;
        int maxIndexI = 0;
        int maxIndexJ = 0;
        int tmpMax = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if ((matr[i][j] > matr[i][j - 1])) {
                    tmpMax += 1;
                    if (tmpMax > max) {
                        max += 1;
                        maxIndexI = i;
                        maxIndexJ = j;
                    }
                }
            }
            tmpMax = 0;
        }

        System.out.println("");
        for (int j = maxIndexJ - 1; j < maxIndexJ + max - 1; j++) {
            System.out.print(matr[maxIndexI][j] + "\t");
        }

    }

    private static void task15() {
        System.out.println("15. Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.");
        int[][] matr = getMatrix();
        printMatr(matr);
        int sum = 0;
        boolean tmp = false;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if ((flag) && (matr[i][j] > 0)) {
                    if (tmp) {
                        flag = false;
                        break;
                    } else {
                        tmp = true;
                    }

                } else {
                    if (flag && tmp) {
                        sum += matr[i][j];
                    }
                }
            }

        }
        System.out.println("sum= " + sum);

    }

    private static void task17() {
        System.out.println("17. Вычислить определитель матрицы.");
        double[][] matr = new double[n][n];
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                Random random = new Random();
                matr[i][j] = -n + random.nextInt(2 * n + 1);
            }
        }
        printMatrDouble(matr);
        System.out.println("");
        class Opr {

            //рекурсивная функция - вычисляет значение определителя. Если на входе определитель 2х2 - просто вычисляем (крест-на-крест),
            //иначе раскладываем на миноры. Для каждого минора вычисляем ЕГО определитель, рекурсивно вызывая ту же функцию..
            public double calculateMatrix(double[][] matrix) {
                double calcResult = 0.0;
                if (matrix.length == 2) {
                    calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
                } else {
                    int koeff = 1;
                    for (int i = 0; i < matrix.length; i++) {
                        if (i % 2 == 1) {  //я решил не возводить в степень, а просто поставить условие - это быстрее. Т.к. я раскладываю всегда по первой (читай - "нулевой") строке, то фактически я проверяю на четность значение i+0.
                            koeff = -1;
                        } else {
                            koeff = 1;
                        };
                        //собственно разложение:                
                        calcResult += koeff * matrix[0][i] * this.calculateMatrix(this.getMinor(matrix, 0, i));
                    }
                }

                //возвращаем ответ
                return calcResult;
            }

            //функция, к-я возвращает нужный нам минор. На входе - определитель, 
            //из к-го надо достать минор и номера строк-столбцов, к-е надо вычеркнуть.
            private double[][] getMinor(double[][] matrix, int row, int column) {
                int minorLength = matrix.length - 1;
                double[][] minor = new double[minorLength][minorLength];
                int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
                int dJ = 0;
                for (int i = 0; i <= minorLength; i++) {
                    dJ = 0;
                    for (int j = 0; j <= minorLength; j++) {
                        if (i == row) {
                            dI = 1;
                        } else {
                            if (j == column) {
                                dJ = 1;
                            } else {
                                minor[i - dI][j - dJ] = matrix[i][j];
                            }
                        }
                    }
                }

                return minor;

            }
        }

        Opr opr = new Opr();
        double Result = opr.calculateMatrix(matr);
        System.out.println(Result);
        return;
    }

    private static void task18() {
        System.out.println("18. Найти максимальный элемент(ы) в матрице и удалить из матрицы все строки и столбцы, его содержащие.");
        int[][] matr = getMatrix();
        printMatr(matr);
        int max = matr[0][0];
        int maxi = 0;
        int maxj = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matr[i][j] > max) {
                    max = matr[i][j];
                    maxi = i;
                    maxj = j;
                }
            }

        }
        System.out.println("max = " + max);

        for (int j = 0; j < n; j++) {
            matr[maxi][j] = 0;
        }
        for (int i = 0; i < n; i++) {
            matr[i][maxj] = 0;
        }
        printMatr(matr);
    }

    private static void task19() {
        System.out.println("19. Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.");
        int[][] matr = new int[n][n];
        boolean[] column = {true, true, true, true};
        boolean[] row = new boolean[n];
        int n = 0, m = 0;
        for (int i = 0; i < matr.length - 1; i++) {
            for (int j = 0; j < matr.length; j = j + 2) {
                Random random = new Random();
                matr[i][j] = random.nextInt(6);
            }
        }
        printMatr(matr);
        boolean bol = true;
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr.length; j++) {
                if (matr[i][j] != 0) {
                    bol = true;
                    break;
                } else {
                    bol = false;
                }
            }
            if (bol == false) {
                row[i] = true;
                n++;
            }

        }
        bol = true;
        for (int j = 0; j < matr.length; j++) {
            for (int i = 0; i < matr.length; i++) {
                if (matr[i][j] != 0) {
                    bol = true;
                    break;
                } else {
                    bol = false;
                }

            }
            if (bol) {
                column[j] = false;
                m++;
            }

        }
        System.out.println("column  " + Arrays.toString(column));
        System.out.println("row  " + Arrays.toString(row));

        System.out.println("n  " + n);
        System.out.println("m  " + m);

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr.length; j++) {
                if ((row[j]) && (column[i])) {
                    System.out.print(matr[i][j] + "\t");
                }
            }
            System.out.println(" ");
        }

    }

    private static void task20() {
        System.out.println("20. В матрице найти минимальный элемент и переместить"
                + " его на место заданного элемента путем перестановки строк и столбцов.");
        int[][] matr = getMatrix();
        printMatr(matr);
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку заданного элемента: ");
        int row = in.nextInt();
        System.out.println("Введите столбец заданного элемента: ");
        int col = in.nextInt();
        int min = matr[0][0];
        int minI = 0;
        int minJ = 0;
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                if (min > matr[i][j]) {
                    min = matr[i][j];
                    minI = i;
                    minJ = j;
                }
            }
        }
        System.out.println("min= " + min);

        for (int i = 0; i < n; i++) {
            int tmp = matr[minI][i];
            matr[minI][i] = matr[row][i];
            matr[row][i] = tmp;
        }

        for (int i = 0; i < n; i++) {
            int tmp = matr[i][minJ];
            matr[i][minJ] = matr[i][col];
            matr[i][col] = tmp;
        }
        printMatr(matr);
    }

    private static void task9() {
        System.out.println("9. Написать программу, которая выводит числа от 1 до 25 в виде матрицы 5x5 слева направо и сверху вниз.\n");
        int tmp = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tmp + "\t");
                tmp++;
            }
            System.out.println();
        }

    }

    private static void task11() {
        int month = 0;
        System.out.print("Введите число: ");
        Scanner scn = new Scanner(System.in);

        while ((month < 1) || (month > 12)) {
            try {
                month = scn.nextInt();
            } catch (InputMismatchException e) {
                scn.next();
                System.err.println("Не верный ввод!");
            }

        }
        scn.close();

        switch (month) {
            case 1:
                System.out.println("Январь");
                break;
            case 2:
                System.out.println("Февраль");
                break;
            case 3:
                System.out.println("Март");
                break;
            case 4:
                System.out.println("Апрель");
                break;
            case 5:
                System.out.println("Май");
                break;
            case 6:
                System.out.println("Июнь");
                break;
            case 7:
                System.out.println("Июль");
                break;
            case 8:
                System.out.println("Август");
                break;
            case 9:
                System.out.println("Сентябрь");
                break;
            case 10:
                System.out.println("Октябрь");
                break;
            case 11:
                System.out.println("Ноябрь");
                break;
            case 12:
                System.out.println("Декабрь");
                break;
        }
    }

    private static void task16() {
        System.out.println("16. Повернуть матрицу на 90 градусов против часовой стрелки.");
        Random rand = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt();
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                // matrix[i][j]= rand.nextInt();
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

    }

    private static void task22() {
        System.out.println("22. Округлить все элементы матрицы до целого числа.");
        double[][] matrix = new double[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextDouble() * 100;
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.round(matrix[i][j]);
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

    }

    private static void task21() {
        System.out.println("21. Преобразовать строки матрицы таким образом, чтобы элементы,"
                + " равные нулю, располагались после всех остальных.");

        Integer[][] matr = getMatrixInteger();
        printMatrInteger(matr);
        for (Integer[] integers : matr) {
            Arrays.sort(integers, new Comparator<Integer>() {

                public int compare(Integer o1, Integer o2) {
                    if (o1 == 0 && o2 != 0) {
                        return 1;
                    }
                    if (o1 != 0 && o2 == 0) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        System.out.println("");
        printMatrInteger(matr);

    }

    private static Integer[][] getMatrixInteger() {
        Integer[][] matr = new Integer[n][n];
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                Random random = new Random();
                matr[i][j] = -n + random.nextInt(2 * n + 1);
            }
        }
        return matr;
    }

    private static void printMatrInteger(Integer[][] matr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matr[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static void task23() {
        System.out.println("23. Найти количество всех седловых точек матрицы. " + "\n"
                + "(Матрица А имеет седловую точку Аi,j, если Аi,j является " + "\n"
                + "минимальным элементом в i-й строке и максимальным в j-м столбце)");

        int[][] matr = getMatrix();
        printMatr(matr);
        boolean tmp;
        int sedlo = 0;
        int[] minMas = new int[4];
        int min;
        for (int i = 0; i < n; i++) {
            min = matr[i][0];
            for (int j = 0; j < n; j++) {
                if (min > matr[i][j]) {
                    min = matr[i][j];
                    minMas[i] = j;
                }
            }
        }
        //System.out.println(Arrays.toString(minMas));

        for (int j = 0; j < n; j++) {
            tmp = false;
            for (int i = 0; i < n; i++) {
                if (matr[i][minMas[j]] < matr[i][j]) {
                    tmp = true;
                }

            }
            if (tmp == false) {
                sedlo += 1;
            }
        }

        System.out.println("sedlo= " + sedlo);
    }

    private static void task24() {
        System.out.println("24. Перестроить матрицу, переставляя в ней строки так,"
                + " чтобы сумма элементов в строках полученной матрицы возрастала");

        int[][] matr = getMatrix();
        printMatr(matr);
        Comparator<int[]> comparator = new Comparator<int[]>() {

            public int compare(int[] o1, int[] o2) {
                int a1 = 0;
                int a2 = 0;
                for (int i = 0; i < o1.length; i++) {
                    a1 += o1[i];
                    a2 += o2[i];
                }
                if (a1 < a2) {
                    return -1;
                }
                if (a1 > a2) {
                    return 1;
                }
                return 0;
            }
        };
        Arrays.sort(matr, comparator);
        System.out.println("");
        printMatr(matr);

    }

    private static void task25() {
        System.out.println("25. Найти число локальных минимумов. (Соседями элемента" + "\n"
                + " матрицы назовем элементы, имеющие с ним общую сторону или угол." + "\n"
                + " Элемент матрицы называется локальным минимумом, если он строго" + "\n"
                + " меньше всех своих соседей.)");
        int[][] matr = getMatrix();
        printMatr(matr);
        int max = 0, tmp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == 0) {
                        if ((matr[i][j] < matr[i + 1][j]) && (matr[i][j] < matr[i][j + 1])) {

                            tmp += 1;
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i + 1][j])) {
                            tmp += 1;
                        }
                    } else {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i][j + 1]) && ((matr[i][j] < matr[i + 1][j]))) {
                            tmp += 1;
                        }
                    }

                }
                if ((i > 0) && (i < matr.length - 2)) {
                    if (j == 0) {
                        if ((matr[i][j] < matr[i + 1][j]) && (matr[i][j] < matr[i][j + 1]) && (matr[i][j] < matr[i + 1][j])) {
                            tmp += 1;
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i + 1][j]) && (matr[i][j] < matr[i + 1][j])) {
                            tmp += 1;
                        }
                    } else {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i][j + 1]) && (matr[i][j] < matr[i + 1][j]) && (matr[i][j] < matr[i + 1][j])) {
                            tmp += 1;
                        }
                    }
                }
                if (i == matr.length - 1) {
                    if (j == 0) {
                        if ((matr[i][j] < matr[i][j + 1]) && (matr[i][j] < matr[i - 1][j])) {
                            tmp += 1;
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i - 1][j])) {
                            tmp += 1;
                        }
                    } else {
                        if ((matr[i][j] < matr[i][j - 1]) && (matr[i][j] < matr[i - 1][j])) {
                            tmp += 1;
                        }
                    }
                }

            }
        }
        System.out.println("max= " + tmp);

    }

    private static void task26() {
        System.out.println("26. Найти наибольший среди локальных максимумов. " + "\n"
                + "(Элемент матрицы называется локальным максимумом, если он " + "\n"
                + "строго больше всех своих соседей.)");
        int[][] matr = getMatrix();
        printMatr(matr);
        int max = 0, tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == 0) {
                        if ((matr[i][j] > matr[i + 1][j]) && (matr[i][j] > matr[i][j + 1])) {
                            max = matr[i][j];
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i + 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    } else {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i][j + 1]) && ((matr[i][j] > matr[i + 1][j]))) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    }

                }
                if ((i > 0) && (i < matr.length - 2)) {
                    if (j == 0) {
                        if ((matr[i][j] > matr[i + 1][j]) && (matr[i][j] > matr[i][j + 1]) && (matr[i][j] > matr[i + 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i + 1][j]) && (matr[i][j] > matr[i + 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    } else {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i][j + 1]) && (matr[i][j] > matr[i + 1][j]) && (matr[i][j] > matr[i + 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    }
                }
                if (i == matr.length - 1) {
                    if (j == 0) {
                        if ((matr[i][j] > matr[i][j + 1]) && (matr[i][j] > matr[i - 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    } else if (j == matr.length - 1) {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i - 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    } else {
                        if ((matr[i][j] > matr[i][j - 1]) && (matr[i][j] > matr[i - 1][j])) {
                            if (max < matr[i][j]) {
                                max = matr[i][j];
                            }
                        }
                    }
                }

            }
        }
        System.out.println("max= " + max);

    }

    private static void task27() {
        System.out.println("27. Перестроить заданную матрицу, переставляя в ней "
                + "столбцы так, чтобы значения их характеристик убывали. (Характеристикой " + "\n"
                + "столбца прямоугольной матрицы называется сумма модулей его элементов).");

        int[][] matr = getMatrix();
        printMatr(matr);
        Comparator<int[]> comparator = new Comparator<int[]>() {

            public int compare(int[] o1, int[] o2) {
                int a1 = 0;
                int a2 = 0;
                for (int i = 0; i < o1.length; i++) {
                    a1 += Math.abs(o1[i]);
                    a2 += Math.abs(o2[i]);
                }
                if (a1 > a2) {
                    return -1;
                }
                if (a1 < a2) {
                    return 1;
                }
                return 0;
            }
        };
        Arrays.sort(matr, comparator);
        System.out.println("");
        printMatr(matr);

    }

    private static void task10() {
        System.out.println("10. Написать программу, позволяющую корректно находить " + "\n"
                + "корни квадратного уравнения. Параметры уравнения должны задаваться" + "\n"
                + " с командной строки.");
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите a:");
        double a = 0;

        while (true) {
            try {
                a = scn.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Не правильный ввод");
                scn.next();
                continue;
            }
            break;
        }

        double b = 0;
        System.out.println("Введите b:");
        while (true) {
            try {
                b = scn.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Не правильный ввод");
                scn.next();
                continue;
            }
            break;
        }

        double c = 0;
        System.out.println("Введите c:");
        while (true) {
            try {
                c = scn.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Не правильный ввод");
                scn.next();
                continue;
            }
            break;
        }
        scn.close();
        System.out.println(a + "x^2 + " + b + "x + " + c);
        if (a == 0) {
            double x = -c / b;
            System.out.println("x = " + x);
            return;
        }
        double D = b * b - 4 * a * c;
        if (D < 0) {
            System.out.println("D < 0");
        } else if (D == 0) {
            double x = -b / (2 * a);

            System.out.println("x = " + x);
        } else if (D > 0) {
            double d = Math.sqrt(D);
            double x1 = (-b + d) / (2 * a);
            double x2 = (-b - d) / (2 * a);
            System.out.println("D= " + d);
            System.out.println("x1 = " + x1 + " x2 = " + x2);
        }
    }

    private static void task8() {
        System.out.println("8. Ввести n слов с консоли. Среди слов, состоящих" + "\n"
                + " только из цифр, найти слово-палиндром. Если таких слов больше" + "\n"
                + " одного, найти второе из них.");

        String[] tmp = consoleReader();
        for (String str : tmp) {
            if (isPalindrom(str)) {
                System.out.print("Палиндромов: ");
                System.out.println(str);
            } else {
                System.out.println("Палиндромов нет!");
            }
        }

    }

    private static boolean isPalindrom(String str) {
        str = str.trim();
        char[] orig = str.toCharArray();
        boolean g = false;
        for (int i = 0, j = orig.length - 1; (i < j); i++, j--) {
            if (orig[i] != orig[j]) {
                g = false;
                break;
            } else {
                g = true;
            }
        }
        //System.out.println("is palindrom: "+g);
        return g;
    }

    private static void task7() {
        System.out.println("7. Ввести n слов с консоли. Найти слово," + "\n"
                + "  состоящее только из различных символов. Если таких" + "\n"
                + " слов несколько, найти первое из них");

        String[] str = consoleReader();
        for (String s : str) {
            if (isDifferSymbol(s)) {
                System.out.println(s);
                break;
            }
        }

    }

    private static boolean isDifferSymbol(String str) {
        str = str.trim();
        char[] ch = str.toCharArray();
        boolean bol = true;
        for (int i = 0; i < ch.length; i++) {
            if (str.lastIndexOf(ch[i]) != i) {
                bol = false;
                break;
            }
        }
        //System.out.println("is differ symbol: " + bol);
        return bol;
    }

    private static void task6() {
        System.out.println("6. Ввести n слов с консоли. Найти слово," + "\n"
                + "  символы в котором идут в строгом порядке возрастания их кодов. " + "\n"
                + "Если таких слов несколько, найти первое из них.");

        String[] str = consoleReader();

        boolean b = true;
        for (String s : str) {
            char[] ch = s.toCharArray();

            for (int i = 1; i < ch.length; i++) {

                if (ch[i] < ch[i - 1]) {
                    b = false;
                    break;
                }
            }
            if (b) {
                System.out.println(s);
                break;
            }
        }

    }

    private static void task5() {
        System.out.println("5. Ввести n слов с консоли. Найти количество слов," + "\n"
                + "  содержащих только символы латинского алфавита, а среди них –" + "\n"
                + "количество слов с равным числом гласных и согласных букв. ");
        String[] str = consoleReader();
        Pattern pattern = Pattern.compile("^[A-Za-z]*$");
        String[] latin = new String[str.length];
        int i = 0;
        for (String s : str) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                latin[i] = s;
                i++;
            }
        }

        Pattern p = Pattern.compile("[Aa]|[Ee]|[Yy]|[Uu]|[Ii]|[Oo]");
        for (String s : latin) {
            if (s != null) {
                Matcher m = p.matcher(s);
                int counter = 0;
                while (m.find()) {
                    counter++;
                }
                if (counter == s.length() - counter) {
                    System.out.println(s);
                }
            }
        }

    }

    private static void task4() {
        System.out.println("4. Ввести n слов с консоли. Найти слово, в котором число" + "\n"
                + " различных символов минимально. Если таких слов несколько, найти первое из них. ");
        String[] str = consoleReader();
        int tmp;
        int[] size = new int[n];
        boolean bol = true;
        int min = str[0].length();
        int minI = 0;
        for (int k = 0; k < str.length; k++) {
            char[] c = str[k].toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (str[k].lastIndexOf(c[i]) != i) {
                    size[k] += 1;

                }
            }
            if (min > size[k]) {
                min = size[k];
                minI = k;
            }
        }

        System.out.println(str[minI]);

    }

    private static void task2() {
        System.out.println("2. Ввести n строк с консоли. Упорядочить и вывести строки" + "\n"
                + " в порядке возрастания  значений их длины.");

        String[] str = consoleReader();

        Comparator<String> stringComparator = (o1, o2) -> o1.length() - o2.length();
        Arrays.sort(str, stringComparator);

        System.out.println(Arrays.toString(str));
    }

    private static void task1() {
        System.out.println("1. Ввести n строк с консоли, найти самую короткую и" + "\n"
                + " самую длинную строки. Вывести найденные строки и их длину.");

        String[] str = consoleReader();
        
    }

}
