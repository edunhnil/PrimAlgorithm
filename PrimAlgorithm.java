/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primalgorithm;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Admin
 */

public class PrimAlgorithm {
    public static void main(String[] args) {
    {
        int adjacency_matrix[][];
        int number_of_vertices;
        Scanner scan = new Scanner(System.in);
        
// step 1:nhap vao ma tran cua do thi
        try
        {
            System.out.println("Enter the number of vertices");
            number_of_vertices = scan.nextInt();
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];
            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
                {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0)  // chuyen tat ca cac diem ko noi voi nhau thanh INFINITY(999)
                    {
                        adjacency_matrix[i][j] = 999;
                    }
                }
            }
            
// step 2:  luu so dinh cua do thi
            Prims prims = new Prims(number_of_vertices);
            
// step 3:  tim spanning tree
            prims.primsAlgorithm(adjacency_matrix);
            
// step 4:  in ra spanning tree
            prims.printMST();
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
    }
}
