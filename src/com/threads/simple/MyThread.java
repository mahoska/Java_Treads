package com.threads.simple;

import java.util.concurrent.Semaphore;

public class MyThread implements Runnable{


	private Semaphore sem;
    private String name;
    private static int interval;
    
    MyThread(Semaphore sem, String name){
        this.sem=sem;
        this.name=name;
    }
      
    public Semaphore getSem() {
		return sem;
	}

	public void setSem(Semaphore sem) {
		this.sem = sem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int interval) {
		MyThread.interval = interval;
	}

	public void run(){
         
        try{
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            Pinging ping = new Pinging();
            ping.setAddress("google.com");
    		System.out.println(ping.testingAvailabilityOfResource());
    		
            Thread.sleep(interval);
            
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
        System.out.println(name + " освобождает разрешение");
        
        sem.release();
    }
}
