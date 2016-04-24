package com.mdsap.esp.metrobus.load.websocket.executor;

import java.util.Date;

import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.mdsap.esp.metrobus.load.websocket.client.SimpleEchoClient;
import com.mdsap.esp.metrobus.load.websocket.client.SimpleEchoSocket;
import com.mdsap.esp.metrobus.load.websocket.model.Station;

public class MetrobusExcecutor {

 public static long total_sent=0;
	public static void main(String[] args) {
	
		WebSocketClient client = new WebSocketClient();
		SimpleEchoClient echo = new SimpleEchoClient();
		SimpleEchoSocket socket = echo.getSocket(client);

		Station zincirliKuyu=new Station("ZincirliKuyu",23,3496,2500,2700);

		StationExecutor zincirliKuyuStation=new StationExecutor(zincirliKuyu,socket);
		System.out.println("start time...:"+(new Date()));
		zincirliKuyuStation.start();
		

		zincirliKuyuStation.await();
		try {
            client.stop();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end time..:"+(new Date()));
		System.out.println("avg  time..:"+(double)(total_sent/3496));

	}

}
