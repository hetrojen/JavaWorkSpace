package com.banking.sca.exceptionhandler.util;

public class EmailCounter extends Thread{
    private int numberOfEmailsPerHour=0;
    
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	while (!Thread.currentThread().isInterrupted()) {
			System.out.println("Email Counter Started...:");
    		try {
				Thread.sleep(60*60*1000);
				numberOfEmailsPerHour=0;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    
    }

	public int getNumberOfEmailsPerHour() {
		return numberOfEmailsPerHour;
	}
    public void increaseCounter(){
    	numberOfEmailsPerHour++;
    }
}
