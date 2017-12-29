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


	public String testingAvailabilityOfResource() {
		StringBuffer result = new StringBuffer();
	    try {
	      InetAddress inet = InetAddress.getByName(address);
	      System.out.println("Sending Ping Request to " + address);
	      if (inet.isReachable(TIMEOUT)){
	    	  result.append(address + "--<is reachable>");
	      } else {
	    	  result.append(address + "--<isn't reachable>");
	      }
	    } catch(UnknownHostException ex){
	    	  //System.out.println(ex.toString()); 
	    }
	    catch ( Exception ex ) {
	      System.out.println("Exception:" + ex.getMessage());
	    }
	   
	    return result.toString();
	}
	 
}
