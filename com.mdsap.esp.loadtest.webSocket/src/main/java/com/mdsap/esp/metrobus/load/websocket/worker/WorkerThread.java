package com.mdsap.esp.metrobus.load.websocket.worker;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.mdsap.esp.metrobus.load.websocket.client.SimpleEchoClient;
import com.mdsap.esp.metrobus.load.websocket.client.SimpleEchoSocket;
import com.mdsap.esp.metrobus.load.websocket.executor.MetrobusExcecutor;
import com.mdsap.esp.metrobus.load.websocket.model.Station;

public class WorkerThread implements Runnable{
private CountDownLatch countDownLatch;
private Station station;
private int turnstileId;
private SimpleEchoSocket socket;
public WorkerThread(CountDownLatch countDownLatch, Station station, int turnstileId, SimpleEchoSocket socket) {
	// TODO Auto-generated constructor stub
	this.countDownLatch=countDownLatch;
	this.station=station;
	this.turnstileId=turnstileId;
	this.socket=socket;
	
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String stationName= station.getStationName();
		int totalCountPerTurnStile= station.getTotalPassCount()/station.getTurnstileCount();
		Random rn = new Random();
		
		int diff=station.getMaxPassInterval()-station.getMinPassInterval();
		Random per= new Random();
		
		  for(int i=0;i<totalCountPerTurnStile;i++){
			  
			  int waitTime=station.getMinPassInterval();
			  
			  if(per.nextInt(100)>80){
				  waitTime+=per.nextInt(diff);
			  }
			
			  try {
				  long start=System.currentTimeMillis();
			   socket.sendMessage(SimpleEchoClient.getJSONMesage(stationName, turnstileId, i));
			    long end=(System.currentTimeMillis()-start);
			    MetrobusExcecutor.total_sent+=end;
			    
			   //System.out.println(SimpleEchoClient.getJSONMesage(stationName, turnstileId, i));
			  } catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  //System.out.println("ss..:"+waitTime);
			  try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	  System.err.println( "station name..:"+stationName+" turnikeId..:"+this.turnstileId +" passed");
			  
		  }
		countDownLatch.countDown();
	}

}
