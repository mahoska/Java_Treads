package com.threads.simple;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Thread t = Thread.currentThread(); // получаем главный поток
	    //System.out.println(t.getName()); // main
		

		//Pinging p =  new Pinging();
		//p.setAddress("google.com");
		//System.out.println(p.testingAvailabilityOfResource());
		
		
		
		Scanner in = new Scanner(System.in);
		String buffer = new String();
		 
		ArrayList<Map<String, String>> resourse =  new ArrayList<Map<String, String>>(); 
		String answer = "y";
		
		System.out.print("Would you like to specify a network device for checking availability (y- yes, n-no)");
		buffer=in.nextLine(); 
		answer = buffer;
		 
		while(!answer.equals("n")) {
			 Map<String, String> res = new HashMap<String, String>();
			 System.out.print("set the address of the resource to listen to: ");  
			 buffer=in.nextLine(); 
			 res.put("address", buffer);
			 System.out.print("Set the thread priority if you want or 0: ");  
			 buffer=in.nextLine(); 
			 res.put("priority", buffer);
			 //System.out.print(res);
			 resourse.add(res);
			 
			 
			 System.out.print("Would you like to specify a network device for checking availability (y- yes, n-no)");
			 buffer=in.nextLine(); 
			 answer = buffer;
		}
		
		 
		int listeningTime = 60;
		int maxCountThread = 3;
		
		 Semaphore sem = new Semaphore(maxCountThread); 
		 
		 long  start =  new Date().getTime();
		 
		
		
			 for( Map<String, String> res : resourse) {
				 //System.out.println(res);
				 Sender name = new Sender(sem, res.get("address"), Integer.parseInt(res.get("priority")), start, listeningTime);
				 name.start();
				
			}
			 
		
		 
//
//	
//		 
//		
//		
//		 long  start =  new Date().getTime();
//		 while((new Date().getTime()-start)/1000 != 1){
//			 sender1.run();
//			 sender2.run();
//			 sender3.run();
//			 sender4.run();
//		 }
		 
		    
		 
		
		
		
		
	}

}


//https://metanit.com/java/tutorial/8.1.php
//https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
//http://www.javacodex.com/Networking/Ping-IP-Address
//https://habrahabr.ru/post/164487/
