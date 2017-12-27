package com.threads.simple;

public class Main {

	public static void main(String[] args) {
		//Thread t = Thread.currentThread(); // получаем главный поток
	    //System.out.println(t.getName()); // main

		Pinging p =  new Pinging();
		p.setAddress("google.com");
		System.out.println(p.testingAvailabilityOfResource());
		
	}

}


//https://metanit.com/java/tutorial/8.1.php
//https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html
//http://www.javacodex.com/Networking/Ping-IP-Address
//https://habrahabr.ru/post/164487/
