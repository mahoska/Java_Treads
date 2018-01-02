package com.threads.simple;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Sender extends Thread{

	private Semaphore sem;
	
    private static int threadCount = 0;
    private final int id = threadCount++;
    
    
    private static final int interval = 5000;
    
    private long startTime;//timestamp
    private int workTime;//second
    
	 private transient int priority; 
	 private transient String pingResourse;
	 private boolean statusResourse;
	 
	 
	public String getResult() {
		return "Thread ¹" + id + " - " + 
				new Date(System.currentTimeMillis()) +" - " + 
				this.getPingResourse() + " - " + 
				(this.getStatusResourse() ?  "--<is reachable>"  : "--<isn't reachable>");
	}
	   
	 public boolean getStatusResourse() {
		 return this.statusResourse;
	}


	public void setStatusResourse(boolean status) {
		this.statusResourse = status;
	}


	Sender(Semaphore sem, String  pingResourse, int priority, long startTime, int workTime){
	     this.sem=sem;
	     this.setPriority(priority);
	     this.setPingResourse(pingResourse);
	     this.setWorkTime(workTime);
	     this.setStartTime(startTime);
	     System.out.println(id);
	  }
		 
	 
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


	public void run(){
        try{
            sem.acquire();
	        while(((new Date().getTime()-startTime))/1000 <=workTime && !this.isInterrupted()) { 
	        	//if(!Thread.interrupted ()) {
		            Pinging ping = new Pinging();
		            ping.setAddress(pingResourse);	            
		            this.setStatusResourse(ping.testingAvailabilityOfResource());
		            System.out.println(this.getResult());
		            System.out.println();
		    		TimeUnit.MILLISECONDS.sleep(interval);
	        	//}
	        } 
	        System.out.println("thread ¹"+ id + " stoped");
	        threadCount--;
	        
	        if(threadCount==0)
	        	System.out.println("All threads stoped");
        }catch(UnknownHostException ex){ 
        	System.out.println("Thread ¹" + id + " - " + 
    				new Date(System.currentTimeMillis()) +" - " + 
    				this.getPingResourse() + " - " + 
    				" - unknown Host");
        	System.out.println();
    		this.interrupt();
    		threadCount--;
    		//sem.release();
    		//return;
        }catch(InterruptedException e) {
        	this.interrupt();
        	threadCount--;
        	// sem.release();
        	//return;
        }   
        
        sem.release();
    }
	
}
