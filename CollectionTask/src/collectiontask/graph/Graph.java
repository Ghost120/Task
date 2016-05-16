/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontask.graph;

import java.util.*;


/**
 *
 * @author Konstantin Kasyanenko
 */
public class Graph {
    private Map<Integer, Vertex> vertexes= new HashMap<>();
    private int quantityVertex;
   
    public Graph(int quantity) {
        quantityVertex =  quantity;
    }

    public Map<Integer, Vertex> getVertexes() {
        return vertexes;
    }

    public void addSide(int id1, int id2) {
        try {
            Vertex v1 = vertexes.get(id1);
            Vertex v2 = vertexes.get(id2);
            v1.getListOfNeighbours().add(v2);
            v2.getListOfNeighbours().add(v1);
        } catch (NullPointerException e) {
           
        }
    }

    public void removeSide(int id1, int id2) {
        try {
            Vertex v1 = vertexes.get(id1);
            Vertex v2 = vertexes.get(id2);
            v1.getListOfNeighbours().remove(v2);
            v2.getListOfNeighbours().remove(v1);
        } catch (NullPointerException e) {
           
        }
    }
}
