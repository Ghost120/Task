/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontask.graph;

import java.util.ArrayList;

/**
 *
 * @author asus 123
 */
class Vertex {

    private int id;
    private ArrayList<Vertex> listOfNeighbours;
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    ArrayList<Vertex> getListOfNeighbours() {
        return listOfNeighbours;
    }

    Vertex(int id) {
        this.id = id;
    }
}
