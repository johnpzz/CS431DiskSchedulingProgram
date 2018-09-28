/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS431;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author John
 */
public class Driver {
    
    public static void myAlgorithm(Queue<Integer> queue)
    {
        ArrayList<Integer> q = new ArrayList<>();
        q.addAll(queue);
        
        ArrayList<seqReq> seqReqQueue = new ArrayList<>();
        ArrayList<seqReq> bestList = new ArrayList<>();

       
        int time = 0;
        int cylinder = 50;
        int numRequests = q.size();
        int totalDelay = 0;
        int maxDelay = 0;
        double totalScore = 0;
        double maxScore = 0;
        
        //System.out.println("Number of requests: " + numRequests);
        
        //grab first 10 from master list
        if (q.size() >= 10)
        {
            for (int i = 0; i < 10; i++)
            {
                seqReq request = new seqReq();
                int thisSeek = q.remove(0);
                request.cylinder = thisSeek;
                request.timeEntered = 0;
                seqReqQueue.add(request);
                
            }
        }
        

        //Running simulation.. removing instances from seqReqQueue..
        
        int direction = 1;  // 1 represents positive/up/right

        while (!seqReqQueue.isEmpty())
        {

            if (seqReqQueue.size() < 5 && q.size() >= 10)
            {
                addTen(q, seqReqQueue, time);
            }
            
            
            seqReq bestReq = seqReqQueue.get(0);
            int best = cylinder - bestReq.cylinder;
            
            boolean found = false;            
            
                for (seqReq i : seqReqQueue)
                {
                    int value = i.cylinder - cylinder;
                    
                    if (value >= 0 && direction == 1 || value <=0 && direction == 0)
                    {
                        
                            found = true;
                            int cylinderDifference = Math.abs(cylinder - i.cylinder);
                            if (cylinderDifference < best)
                            {
                                best = cylinderDifference;
                                bestReq = i;
                            }
                        
                    }
                }
                //no shortest found in this direction, change direction and do again
                if (!found)
                {
                    if (direction == 1)
                    {
                        direction = 0;
                    }
                    else 
                    {
                        direction = 1;
                    }
                    continue;
                }
              
            
            int newCylinder = bestReq.cylinder;
            cylinder = newCylinder;
            time += best;
            bestReq.timeSatisfied = time;
            
            
            bestList.add(bestReq);
            seqReqQueue.remove(bestReq);
          
         }
        
        maxDelay = bestList.get(0).getDelay();
        maxScore = bestList.get(0).getScore();
        for (seqReq a : bestList)
        {
            //System.out.println(a.cylinder);
            
            totalScore += a.getScore();
            totalDelay += a.getDelay();
            
            if (a.getScore() > maxScore)
            {
                maxScore = a.getScore();
            }
            
            if (a.getDelay() > maxDelay)
            {
                maxDelay = a.getDelay();
            }
        }

        double avgScore = totalScore/numRequests;
        double avgDelay = totalDelay/numRequests;
        
        
        System.out.print("My Algorithm..");
        System.out.println("\nAvg Delay: " + avgDelay + "\nMaximum Delay: " + maxDelay + "\nAvg Score: " + avgScore + 
                "\nMax Score: " + maxScore);
       
        
    }
    
    
    
    public static void elevator(Queue<Integer> queue)
    {
        ArrayList<Integer> q = new ArrayList<>();
        q.addAll(queue);
        
        ArrayList<seqReq> seqReqQueue = new ArrayList<>();
        ArrayList<seqReq> bestList = new ArrayList<>();

       
        int time = 0;
        int cylinder = 50;
        int numRequests = q.size();
        int totalDelay = 0;
        int maxDelay = 0;
        double totalScore = 0;
        double maxScore = 0;
        
        //System.out.println("Number of requests: " + numRequests);
        
        //grab first 10 from master list
        if (q.size() >= 10)
        {
            for (int i = 0; i < 10; i++)
            {
                seqReq request = new seqReq();
                int thisSeek = q.remove(0);
                request.cylinder = thisSeek;
                request.timeEntered = 0;
                seqReqQueue.add(request);
                
            }
        }
        

        //Running simulation.. removing instances from seqReqQueue..
        
        //INITIAL DIRECTION IS UP.. WE ARE GOING RIGHT FIRST
        int direction = 1; // 1 represents UP/positive/right
        
        while (!seqReqQueue.isEmpty())
        {

            if (seqReqQueue.size() < 5 && q.size() >= 10)
            {
                addTen(q, seqReqQueue, time);
            }
            
            seqReq bestReq = seqReqQueue.get(0);
            int best = Math.abs(cylinder - bestReq.cylinder);
            
            boolean found = false;
            
                for (seqReq i : seqReqQueue)
                {
                    int value = i.cylinder - cylinder;
                    
                    if (value >= 0 && direction == 1 || value <= 0 && direction == 0)
                    {
                            found = true;
                            int cylinderDifference = Math.abs(cylinder - i.cylinder);
                            if (cylinderDifference < best)
                            {
                                best = cylinderDifference;
                                bestReq = i;
                            }
                    }
                }
                
                //no shortest found in this direction, change direction and do again
                if (found == false)
                {
                    if (direction == 1)
                    {
                        direction = 0;
                    }
                    else 
                    {
                        direction = 1;
                        
                    }
                    continue;
                }
                
            
            int newCylinder = bestReq.cylinder;
            cylinder = newCylinder;
            time += best;
            bestReq.timeSatisfied = time;
            
            
            bestList.add(bestReq);
            seqReqQueue.remove(bestReq);
          
         }
        
        maxDelay = bestList.get(0).getDelay();
        maxScore = bestList.get(0).getScore();
        for (seqReq a : bestList)
        {
            //System.out.println(a.cylinder);
            
            totalScore += a.getScore();
            totalDelay += a.getDelay();
            
            if (a.getScore() > maxScore)
            {
                maxScore = a.getScore();
            }
            
            if (a.getDelay() > maxDelay)
            {
                maxDelay = a.getDelay();
            }
        }

        double avgScore = totalScore/numRequests;
        double avgDelay = totalDelay/numRequests;
        
        
        System.out.print("Elevator Algorithm..");
        System.out.println("\nAvg Delay: " + avgDelay + "\nMaximum Delay: " + maxDelay + "\nAvg Score: " + avgScore + 
                "\nMax Score: " + maxScore);
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void addTen(ArrayList<Integer> queue, ArrayList<seqReq> seqReqQueue, int time)
    { 
        for (int i = 0; i < 10; i++)
        {
            if (!queue.isEmpty())
            {
                seqReq request = new seqReq();
                int value = queue.remove(0);
                request.cylinder = value;
                request.timeEntered = time;
                seqReqQueue.add(request);
                
            }
        }
    }
    
    public static void SSF(Queue<Integer> queue)
    {
        ArrayList<Integer> q = new ArrayList<>();
        q.addAll(queue);
        
        ArrayList<seqReq> seqReqQueue = new ArrayList<>();
        ArrayList<seqReq> bestList = new ArrayList<>();

       
        int time = 0;
        int cylinder = 50;
        int numRequests = q.size();
        int totalDelay = 0;
        int maxDelay = 0;
        double totalScore = 0;
        double maxScore = 0;
        
        //System.out.println("Number of requests: " + numRequests);
        
        //grab first 10 from master list
        if (q.size() >= 10)
        {
            for (int i = 0; i < 10; i++)
            {
                seqReq request = new seqReq();
                int thisSeek = q.remove(0);
                request.cylinder = thisSeek;
                request.timeEntered = 0;
                seqReqQueue.add(request);
                
            }
        }
        

        //Running simulation.. removing instances from seqReqQueue..
        
        while (!seqReqQueue.isEmpty())
        {

            if (seqReqQueue.size() < 5 && q.size() >= 10)
            {
                addTen(q, seqReqQueue, time);
            }
            
            
            
            seqReq bestReq = seqReqQueue.get(0);
            int best = Math.abs(cylinder - bestReq.cylinder);
            
            for (seqReq i : seqReqQueue)
            {
                int cylinderDifference = Math.abs(cylinder - i.cylinder);
                if (cylinderDifference < best)
                {
                    best = cylinderDifference;
                    bestReq = i;
                }
            }
            
            int newCylinder = bestReq.cylinder;
            cylinder = newCylinder;
            time += best;
            bestReq.timeSatisfied = time;
            
            
            bestList.add(bestReq);
            seqReqQueue.remove(bestReq);
          
         }
        
        maxDelay = bestList.get(0).getDelay();
        maxScore = bestList.get(0).getScore();
        for (seqReq a : bestList)
        {
            totalScore += a.getScore();
            totalDelay += a.getDelay();
            
            if (a.getScore() > maxScore)
            {
                maxScore = a.getScore();
            }
            
            if (a.getDelay() > maxDelay)
            {
                maxDelay = a.getDelay();
            }
        }

        double avgScore = totalScore/numRequests;
        double avgDelay = totalDelay/numRequests;
        
        System.out.print("Shortest Seek First Algorithm.. ");
        System.out.println("\nAvg Delay: " + avgDelay + "\nMaximum Delay: " + maxDelay + "\nAvg Score: " + avgScore + 
                "\nMax Score: " + maxScore);
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public static void FCFS(Queue<Integer> queue)
        {
            Queue<Integer> q = new LinkedList<>();
            q.addAll(queue);
            
            int num = q.remove();
            int firstSeek = Math.abs(50 - num);
            int distance = firstSeek;
           
            //System.out.println("First Seek: " + distance);
            
            while (!q.isEmpty())
            {
                int newNum = q.remove();
                distance += Math.abs(newNum - num);
                num = newNum;
            }
            
            System.out.println("Total Seeks for FCFS: " + distance);
        }
    
    public static void main (String[] args)
    {
        System.out.println("Enter number of seeks: ");
        Scanner input = new Scanner(System.in);
        int seeks = input.nextInt();
        
        Queue<Integer> queue = new LinkedList<>();
        
        Random rand = new Random();
       
        for (int i = 0; i < seeks; i++)
        {
            int randomNum = rand.nextInt(100) + 1;
            queue.add(randomNum);
        }
       
        //System.out.println("Test print random elements in queue: ");
        for (int i : queue)
        {
            //System.out.println(i);
        }
        
        
        //System.out.println("\nFCFS: ");
        System.out.println("\n");
        
        
        FCFS(queue);
        System.out.println();
        SSF(queue);
        System.out.println();
        elevator(queue);
        System.out.println();
        myAlgorithm(queue);
            
        
    }
    
}
