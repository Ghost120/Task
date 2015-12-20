/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duplicates;

import java.io.File;
import java.io.IOException;

public class Test {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
     Duplicates dup = new Duplicates();
      dup.process(new File("C:\\Users\\asus 123\\Desktop\\tSystem\\JavaApplication11\\src\\javaapplication11\\file1.txt"), new File("C:\\Users\\asus 123\\Desktop\\tSystem\\JavaApplication11\\src\\javaapplication11\\file2.txt"));

    }
    
}
  
            


