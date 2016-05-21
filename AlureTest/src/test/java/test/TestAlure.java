package test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

/**
 *
 * @author Kasyanenko Konstantin
 */
@Title("This is our cool test suite")
@Description("In this cool suite we will test only cool features")
@RunWith(Parameterized.class)
public class TestAlure {

    private static double operand1;
    private static double operand2;
    private static double result;
    private static String operation;

    public TestAlure(final String operand1, final String operand2, final String operation, final String result) {
        this.operand1 = Double.valueOf(operand1);
        this.operand2 = Double.valueOf(operand2);
        this.result = Double.valueOf(result);
        this.operation = operation;
    }

    @Title("First cool check")
    @Test
    public void hello() {
        System.out.println("operand1: " + operand1 + " operand2: " + operand2
                + " result: " + result + " operation: " + operation);

    }

    @Step
    public void step() {
        assertTrue("Result 1 to 4", 2 * 2 == 4);
    }

    /**
     * Cтатический метод возвращающий список данных, которые затем будут
     * использованы в качестве аргументов конструктора класса.
     *
     * @return
     */
    @Parameterized.Parameters
    public static List<String[]> isEmptyData() {
        List<String> list = readFileToList("src/main/resources/newfile.txt");
        List<String[]> strList = new ArrayList<>();
        list.stream().forEach((s) -> {
            strList.add(s.split(";"));
        });
        return strList;
    }

    /**
     *
     * @param fileName- путь к файлу
     * @return возвращает ArrayList
     */
    private static List<String> readFileToList(String fileName) {
        List<String> list = new ArrayList<>();
        List<String[]> retList = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            list = Files.readAllLines(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        list.stream().forEach((s) -> {
//            retList.add(s.split(";"));
//        });

        //retList.forEach(s->{System.out.println(s.toString());});
        return list;
    }

    private double operation() throws IllegalArgumentException {
        switch (operation) {
            case "+":
                return operand1 + operand2;
            case "*":
                return operand1 * operand2;
            case "-":
                return operand1 - operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operation);
        }
    }
}
