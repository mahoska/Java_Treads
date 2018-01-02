package com.threads.simple;

import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Sender extends Thread{

	private Semaphore sem;
	
    private static int threadCount = 0;
    private final int id = threadCount++;
    
    
    private static final int interval = 5000;
	private transient String pingResourse;
	private boolean statusResourse;
	 
	public static ArrayDeque<String> results = new ArrayDeque<String>();
	 
	Sender(Semaphore sem, String  pingResourse, int priority){
	     this.sem=sem;
	     this.setPriority(priority);
	     this.setPingResourse(pingResourse);
	}
		 
	public String getPingResourse() {
			return pingResourse;
	}

	public void setPingResourse(String pingResourse) {
			this.pingResourse = pingResourse;
	}
	
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

	public void run(){
        try{
            sem.acquire();
            while(!this.isInterrupted()) {
		            Pinging ping = new Pinging();
		            ping.setAddress(pingResourse);	            
		            this.setStatusResourse(ping.testingAvailabilityOfResource());
		            results.add(this.getResult());
		    		TimeUnit.MILLISECONDS.sleep(interval);
	        } 
	        
        }catch(UnknownHostException ex){ 
        	results.add("Thread ¹" + id + " - " + 
    				new Date(System.currentTimeMillis()) +" - " + 
    				this.getPingResourse() + " - " + 
    				" - unknown Host");
    		this.interrupt();
    		
        }catch(InterruptedException e) {
        	this.interrupt();
        }   
        
        sem.release();
    }
}
