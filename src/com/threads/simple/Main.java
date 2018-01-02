package com.threads.simple;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		String buffer = new String();
		 
		ArrayList<Map<String, String>> resourse =  new ArrayList<Map<String, String>>(); 
		String answer = "y";
		
		do {
			System.out.print("Would you like to specify a network device for checking availability (y- yes, n-no)");
			buffer=in.nextLine(); 
			answer = buffer;
		 }while(!buffer.equals("y") && !buffer.equals("n"));
		Matcher m;
		while(!answer.equals("n")) {
			 Map<String, String> res = new HashMap<String, String>();
			 System.out.print("set the address of the resource to listen to: ");  
			 buffer=in.nextLine(); 
			 res.put("address", buffer);
			 do {
			 System.out.print("Set the thread priority if you want or 0: ");  
			 buffer=in.nextLine(); 
			 res.put("priority", buffer);
			 Pattern p = Pattern.compile("^[1-9]$");  
		     m  = p.matcher(buffer);  
			 }while(!m.matches());
			 resourse.add(res);
			 
			 do {
				 System.out.print("Would you like to specify a network device for checking availability (y- yes, n-no)");
				 buffer=in.nextLine(); 
				 answer = buffer;
			 }while(!buffer.equals("y") && !buffer.equals("n"));
		}
		
		

		int listeningTime = 60;
		int maxCountThread = 3;
		int outputInterval = 5000;
		
		Semaphore sem = new Semaphore(maxCountThread); 
		long  start =  new Date().getTime();
		
		ArrayList<Sender> threads = new ArrayList<Sender>();
		
	    for(Map<String, String> res : resourse) {
			threads.add(new Sender(sem, res.get("address"), Integer.parseInt(res.get("priority"))));
			threads.get(threads.size()-1).start();
		}
	    
	    while((new Date().getTime()-start)/1000 <=listeningTime) {
	    	 while(Sender.results.peek()!=null){
	             System.out.println(Sender.results.pop());
	         }
	    	 System.out.println();
	    	 
	    	 TimeUnit.MILLISECONDS.sleep(outputInterval);
	    }
	    
	    for(Sender thread : threads) {
	    	if(!thread.isInterrupted()) {
	    		thread.interrupt();
	    	}
	    }
	    
	    System.out.println("Done");
	    
	}
}


//https://metanit.com/java/tutorial/8.1.php
//https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
//http://www.javacodex.com/Networking/Ping-IP-Address
//https://habrahabr.ru/post/164487/
//https://sites.google.com/site/stas0nxlam/home/parallelnoe-vypolnenie
