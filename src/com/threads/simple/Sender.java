package com.threads.simple;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Sender extends Thread{

	//private Semaphore sem;
	
    private static int threadCount = 0;
    private final int id = threadCount++;
    
    
    private static final int interval = 5000;
    
    private long startTime;//timestamp
    private int workTime;//second
    
	 private transient int priority; 
	 private transient String pingResourse;
	 
	 
	   
	 Sender(/*Semaphore sem, */String  pingResourse, int priority, long startTime, int workTime){
	     //this.sem=sem;
	     this.setPriority(priority);
	     this.setPingResourse(pingResourse);
	     this.setWorkTime(workTime);
	     this.setStartTime(startTime);
	     System.out.println(id);
	  }
		 
	 
//	 public int getPriority() {
//			return priority;
//	}
//
//	public void setPriority(int priority) {
//			this.priority = priority;
//	}

	public String getPingResourse() {
			return pingResourse;
	}

	public void setPingResourse(String pingResourse) {
			this.pingResourse = pingResourse;
	}
	
	

	
    
    public long getStartTime() {
		return startTime;
	}


	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	public int getWorkTime() {
		return workTime;
	}


	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}


//	public Semaphore getSem() {
//		return sem;
//	}
//
//	public void setSem(Semaphore sem) {
//		this.sem = sem;
//	}

	public void run(){
		
		
        try{
        	
           // System.out.println(id + " ожидает разрешение");
            //sem.acquire();
 
            long test ;
	        while(( (new Date().getTime()-startTime))/1000 != workTime) { 

	            Pinging ping = new Pinging();
	            ping.setAddress(pingResourse);
	    		System.out.println("Thread №"+id+" - "+new Date(System.currentTimeMillis())+" - "+ping.testingAvailabilityOfResource());
	    		
	    		 TimeUnit.MILLISECONDS.sleep(interval);
	        }  

        }catch(InterruptedException e) {
        	System.out.println(e.getMessage() + " Sender sleep interrupted");
        }
       
        
        //System.out.println(id + " освобождает разрешение");
        
        //sem.release();
    }
	
}
