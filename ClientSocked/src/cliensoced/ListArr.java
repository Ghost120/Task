/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliensoced;

import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author asus 123
 */

public class ListArr {
    private ArrayList<ArrayList<Point>> ar= new ArrayList<>();
    
    public void SetNewArraylist(int x,int y){
        ArrayList<Point> p= new ArrayList<>();
        p.add(new Point(x, y));
        ar.add(p);
    }
    
    public void SetNewPoint(int x,int y){      
        ar.get(ar.size()-1).add(new Point(x, y));
    }
    
    public ArrayList getTable(){
        return ar;
    }
}
