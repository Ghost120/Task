package cliensoced;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class ClienSoced {

    //public static ArrayList<ArrayList<Point>> table;

    public static void main(String args[]) {
        //ArrayList <ArrayList> al= new ArrayList<ArrayList>();
        //ArrayList<ArrayList<Point>> table= new ArrayList<ArrayList<Point>>();
        ListArr table = new ListArr();
        
        Toolkit kit = Toolkit.getDefaultToolkit();// определяем ширину и высоту экрана
        Dimension screenSize = kit.getScreenSize();
        JFrame frame = new JFrame("Test");
        frame.setBounds(0, 0, screenSize.width, screenSize.height); //устанавливаем размеры экарана
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        try {
            // открываем сокет и коннектимся к localhost:
            // получаем сокет сервера
            Socket clientSocet = new Socket("localhost", 29288);

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocet.getInputStream())); 
            String s;
            while (true) { // ждем прокотол 
                s = inFromServer.readLine();
                getParams(s,table);//парсим протокол и пишем в ArrayList               
                JPanel jp = printTable(table);// обновляем экран
                frame.getContentPane().add(jp);// добавляем.
                frame.validate();
            }

        } catch (Exception e) {
            System.out.println("init error: " + e);// вывод исключений
        } 

    }

    private static JPanel printTable(ListArr table ) {
        JPanel contentPane = new JPanel() {
            Graphics2D g2;
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(5.0f));
                
                ArrayList<ArrayList<Point>> arr=table.getTable();
                
                for (int i=0;i<arr.size();i++) {
                    bezierGoGo(arr.get(i), g2);
                }
            }
        };
        return contentPane;
    }

    

    private static void getParams(String s, ListArr lr) { // разбиваем строку на части 
        ArrayList<String> al = new ArrayList<>();

        String id;
        String act;
        String absX;
        String absY;
        String color;
       
        StringTokenizer st = new StringTokenizer(s, ";");
        while (st.hasMoreTokens()) {
            al.add(st.nextToken());
        }

        if (al.size() == 5) {
            id = al.get(0);
            act = al.get(1);
            absX = al.get(2);
            absY = al.get(3);
            color = al.get(4);
//            System.out.println("id = "+ id);
//            System.out.println("act = "+ act);
//            System.out.println("absX = "+ absX);
//            System.out.println("absY = "+ absY);
//            System.out.println("color = "+ color);
            
            if (act.equals("move")) {
                System.out.println("move");
                lr.SetNewPoint(Integer.parseInt(absX), Integer.parseInt(absY));//добавляем новый эелемент 
            } else if (act.equals("start")) {
                lr.SetNewArraylist(Integer.parseInt(absX), Integer.parseInt(absY));
                System.out.println("Start");
            }

        }

    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    private static void bezierGoGo(ArrayList<Point> sourcePoints, Graphics painter) {
        // ф-ия расчитывает финальный набор точек, по которым будет строится кривуля, а затем рисует ее
        ArrayList<Point> finalPoints = new ArrayList<>();

        for (double t = 0; t <= 1; t += 0.01) {
            finalPoints.add(calculateBezierFunction(t, sourcePoints));
        }
        drawCurve(finalPoints, painter);
    }

    private static Point calculateBezierFunction(double t, ArrayList<Point> srcPoints) {   // ф-ия расчитывает очередную точку на кривой исходя из входного набора управляющих точек
        double x = 0;
        double y = 0;

        int n = srcPoints.size() - 1;
        for (int i = 0; i <= n; i++) {
            x += fact(n) / (fact(i) * fact(n - i)) * srcPoints.get(i).getX() * Math.pow(t, i) * Math.pow(1 - t, n - i);
            y += fact(n) / (fact(i) * fact(n - i)) * srcPoints.get(i).getY() * Math.pow(t, i) * Math.pow(1 - t, n - i);
        }
        return new Point((int) x, (int) y);
    }

    private static double fact(double arg) {
        if (arg < 0) {
            throw new RuntimeException("negative argument.");
        }
        if (arg == 0) {
            return 1;
        }

        double rezult = 1;
        for (int i = 1; i <= arg; i++) {
            rezult *= i;
        }
        return rezult;
    }

    private static void drawCurve(ArrayList<Point> points, Graphics painter) {
        for (int i = 1; i < points.size(); i++) {
            int x1 = (int) (points.get(i - 1).getX());
            int y1 = (int) (points.get(i - 1).getY());
            int x2 = (int) (points.get(i).getX());
            int y2 = (int) (points.get(i).getY());
            painter.drawLine(x1, y1, x2, y2);
        }
    }

}
