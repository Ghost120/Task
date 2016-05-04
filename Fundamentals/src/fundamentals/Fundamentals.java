package fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class Fundamentals {

    private static int n = 4;

    public static void main(String[] args) {
        //task3();
        //task11();
        //task22();
        //task16();
        //task9();
        //task13();
        //task14();
        //task15();
        //task17();
        //task18();
        //task19();//доделать
        //task20();
        //task21();
        task23();
    }

    private static String[] consoleReader() {
        String[] temp = {"qwe qwer", "qwemrtmdf dfg", "wseg sdfg"};
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
        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                Random random = new Random();
                matr[i][j] = -n + random.nextInt(2 * n + 1);
            }
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
        boolean tmp ;
        int sedlo=0;
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
        
        for (int j = 0; j < n; j++) {
            tmp=false;
            for (int i = 0; i < n; i++) {
                if (minMas[j] < matr[i][j]) {
                    tmp =true;
                }     
               
            }
            if(tmp==false){sedlo+=1;}
        }

        for (int i = 0; i < n; i++) {
            System.out.println("minJ= " + minMas[i]);
        }
        System.out.println("sedlo= "+sedlo);
    }
}
