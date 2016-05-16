/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontask.task14;

import java.util.*;

/**
 *
 * @author Konstantin Kasyanenko
 */
public class MyStructureList {

    private List<Integer> list = new ArrayList();

    public void addNumber(int number) {
        list.add(number);
    }

    public void deleteNumber(int number) {
        list.remove(number);
    }

    public int searchNumber(Integer number) {
        int maxblij= Math.abs(number-list.get(0));
        int index=0;
        for(int i=0;i<list.size();i++){
            if(Math.abs(list.get(i)-number)<maxblij){
                maxblij=Math.abs(list.get(i)-number);
                index=i;
            }
        }
        return list.get(index);
    }

    public String getList() {
        return "List: \n" + list.toString();
    }
}
