/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duplicates;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import java.io.File;

/**
 *
 * @author kasyan
 */
public class Duplicates {
    
    boolean process(File Source,File output){
    Map<String,Integer> map = new TreeMap<>();
      String as;     
        try{
            //cоздать файл если он несоздан
            FileWriter fw= new FileWriter(output, true);
            BufferedReader br = new BufferedReader(new FileReader(Source));
            //  System.out.println(br);
            while((as = br.readLine()) !=null){
                if (map.containsKey(as)){   
                    map.put(as, map.get(as)+1);                  
                }else{
                    map.put(as, 1);
                }
            }
            if(output.exists()){
                for(Entry<String,Integer> entry: map.entrySet()){
                   fw.write(entry.getKey()+"["+entry.getValue()+"]\n");
                   System.out.print(entry.getKey()+ "[");
                   System.out.println(entry.getValue()+"]");
                   
                }
                fw.close();
            }else{}
            
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Io Exeption is true!");
        }
        
//        for(Entry<String,Integer> entry: map.entrySet()){
//            System.out.print(entry.getKey()+"["+entry.getValue()+"]\n");
//        } 
    return true;
    }
    
}
