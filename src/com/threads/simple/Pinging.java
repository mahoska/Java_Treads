package com.threads.simple;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pinging {
	
	private String address;
	private final static int TIMEOUT = 5000;
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public boolean testingAvailabilityOfResource() {
		boolean result;
	    try {
	      InetAddress inet = InetAddress.getByName(address);
	      System.out.println("Sending Ping Request to " + address);
	      if (inet.isReachable(TIMEOUT)){
	    	 return true;
	      } else {
	    	  return false;
	      }
	    } catch(UnknownHostException ex){ 
	    		return false;
	    }
	    catch ( Exception ex ) {
	    	return false;
	    }
	}
	 
}
