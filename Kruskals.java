/*
Tyler Echols

CS 3345.002 - Data Structures and Introduction to Algorithmic Analysis

Project #5

Due Dates:  Monday, May 3 at 11:59pm

Submit:    eLearning

Late Policy:  -10 points per hour late

Instructions: This is an individual assignment.  All work should be your own.



Objective:

     Work with graphs and Kruskals algorithm for minimum spanning trees.
*/
import com.sun.javafx.geom.Edge;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class Kruskals
{
    public static class mEdge implements Comparable<mEdge>
    {
        int Distance;
        String V1, V2;

        mEdge(int x, String V1, String V2)
        {
            this.Distance = x;
            this.V1 = V1;
            this.V2 = V2;
        }

        public int compareTo(mEdge x)
        {
        if (this.Distance < x.Distance)
        {
        return -1;
        }
        else
            if (this.Distance > x.Distance)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

    }

    public void kruskal()
    {
     ArrayList<mEdge> listEdge = new ArrayList<>();
     ArrayList<String> VList = new ArrayList<>();
     BufferedReader readFile = null;
     int allV = 0;
     int allDistanceSum = 0;

     final String StoppingPoint = ",";
     try {
         String line = "";
         readFile = new BufferedReader(new FileReader("assn9_data.csv"));
         while ((line = readFile.readLine())!= null){
             String[] tokens = line.split(StoppingPoint);
             VList.add(tokens[0]);
             listEdge.add(new mEdge(Integer.parseInt(tokens[2]), tokens[0], tokens[1]));

             for (int x = 3; x < tokens.length; x++){
                 listEdge.add(new mEdge(Integer.parseInt(tokens[x + 1]), tokens[0], tokens[x]));
                 x++;
             }
             allV++;
         }
     }
     catch (Exception e)
     {
         e.printStackTrace();
     }
     finally
     {
         try
         {
             readFile.close();
         }
         catch (IOException e)
         {
             e.printStackTrace();
         }
     }
        int edgeAt = 0;
        DisjSets x = new DisjSets(VList.size ());
        PriorityQueue<mEdge> PriQueue = new PriorityQueue<>();
        for (mEdge e : listEdge)
        {
            PriQueue.add(e);
        }

        while (edgeAt < VList.size()-1)
        {
        mEdge e = PriQueue.poll();
        if (x.find(VList.indexOf(new String(e.V1))) != x.find(VList.indexOf(new String(e.V2))))
            {
            edgeAt++;
            x.union(x.find(VList.indexOf(new String(e.V1))), x.find(VList.indexOf(new String(e.V2))));
            allDistanceSum = allDistanceSum + e.Distance;
            System.out.println(e.V1 + " - " + e.V2 + " - The travel between the 2 cities: " + e.Distance);
        }

        }
        System.out.println("\n All the distances added together is: " + allDistanceSum);
    }

    public static void main(String[] args)
    {
        Kruskals TheObj = new Kruskals();
        TheObj.kruskal();
    }


}

