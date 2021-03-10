/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primalgorithm;

/**
 *
 * @author Admin
 */
public class Prims {

    private boolean unsettled[];
    private boolean settled[];
    private int numberofvertices;
    private int adjacencyMatrix[][];
    private int key[];
    public static final int INFINITE = 999;
    private int parent[];

    public Prims(int numberofvertices) {
        this.numberofvertices = numberofvertices;  //so dinh cua do thi
        unsettled = new boolean[numberofvertices + 1];  //luu trang thai dinh la chua them vao spanning tree
        settled = new boolean[numberofvertices + 1];  // luu trang thai dinh da them vao spanning tree
        adjacencyMatrix = new int[numberofvertices + 1][numberofvertices + 1];  // ma tran do thi
        key = new int[numberofvertices + 1];  // khoang cach cua ngan nhat cua dinh 
        parent = new int[numberofvertices + 1];  //luu dinh noi den dinh dan xet
    }

    // dem so dinh chua them vao spanning tree
    public int getUnsettledCount(boolean unsettled[]) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++) {
            if (unsettled[index]) {
                count++;
            }
        }
        return count;
    }

    public void primsAlgorithm(int adjacencyMatrix[][]) {
        // sao chep ma tran do thi
        int evaluationVertex;
        for (int source = 1; source <= numberofvertices; source++) {
            for (int destination = 1; destination <= numberofvertices; destination++) {
                this.adjacencyMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }

        for (int index = 1; index <= numberofvertices; index++) {
            key[index] = INFINITE;       //dua gia tri khoang cach so voi diem dang xet mac dinh la infinity
        }

        //doi gia tri diem de xet diem dau tien 
        key[1] = 0;
        unsettled[1] = true;
        parent[1] = 1;

        while (getUnsettledCount(unsettled) != 0) // lap lai cho den khi tat ca diem dc them vao spanning tree
        {
            evaluationVertex = getMimumKeyVertexFromUnsettled(); // tim diem co gia tri khoang cach nho nhat voi cac diem trong spanning tree
            unsettled[evaluationVertex] = false;  // doi trang thai cua diem vua tim dc
            settled[evaluationVertex] = true;  // doi trang thai cua diem vua tim dc
            evaluateNeighbours(evaluationVertex);  // xem xet cac diem xung quanh
        }
    }

    // tim diem co gia tri khoang cach nho nhat voi cac diem trong spanning tree
    private int getMimumKeyVertexFromUnsettled() {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= numberofvertices; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) // di qua tat ca cac diem lay gia tri key be nhat
            {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }

    // xem xet cac diem xung quanh thay doi gia tri key de tiep tuc xet
    public void evaluateNeighbours(int evaluationVertex) {

        for (int destinationvertex = 1; destinationvertex <= numberofvertices; destinationvertex++) {
            if (settled[destinationvertex] == false) // check xem lieu da them vao spanning tree chua
            {
                if (adjacencyMatrix[evaluationVertex][destinationvertex] != INFINITE) // check xem lieu hai diem co noi voi nhau
                {
                    if (adjacencyMatrix[evaluationVertex][destinationvertex] < key[destinationvertex]) // check de cap nhat lai gia tri key
                    {
                        key[destinationvertex] = adjacencyMatrix[evaluationVertex][destinationvertex];
                        parent[destinationvertex] = evaluationVertex;
                    }
                    unsettled[destinationvertex] = true;
                }
            }
        }
    }

    //in ra ket qua
    public void printMST() {
        System.out.println("SOURCE  : DESTINATION = WEIGHT");
        for (int vertex = 2; vertex <= numberofvertices; vertex++) {
            System.out.println(parent[vertex] + "\t:\t" + vertex + "\t=\t" + adjacencyMatrix[parent[vertex]][vertex]);
        }
    }

}
