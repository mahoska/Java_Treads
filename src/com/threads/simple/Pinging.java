package com.threads.simple;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pinging {
	
	private String address;
	private final static int TIMEOUT = 1000;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public boolean testingAvailabilityOfResource() throws UnknownHostException {
		boolean result;
	    try {
	      //System.out.println("Sending Ping Request to " + address);
	      InetAddress inet = InetAddress.getByName(address);
	      if (inet.isReachable(TIMEOUT)){
	    	 return true;
	      } else {
	    	  return false;
	      }
	    } catch(UnknownHostException ex){ 
	    	throw new UnknownHostException();
	    }
	    catch ( Exception ex ) {
	    	return false;
	    }
	}
	 
}
