package com.mdsap.esp.metrobus.load.websocket.client;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mdsap.esp.metrobus.load.websocket.model.Station;
 
/**
 * Example of a simple Echo Client.
 */
public class SimpleEchoClient {
 
    public SimpleEchoSocket  getSocket(WebSocketClient client) {
        String destUri = "ws://esp:9092/publish/iett/iett_metrobus_esp?basic-authorization=ZXNwdXNlcjpwcm9kZXNwMjQ4";
      

        SimpleEchoSocket socket = new SimpleEchoSocket();
        try {
            client.start();
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
          socket.awaitClose(5, TimeUnit.SECONDS);
            return socket;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        } 
    }
    

    
    public static String getJSONMesage(String station, int turnikeId, int sayac)     
    {    

		String formatStr = "yyyy-MM-dd HH:mm:ss:SSS";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);

	    String dateStr= sdf.format(new Date());

    	  JsonObject  streamMessage= new JsonObject();
          streamMessage.addProperty("version", 1);
          streamMessage.addProperty("stream", "turnike_gecis_stream"); 
          streamMessage.addProperty("flags",0);
          JsonArray  rows=new JsonArray();
          
          JsonObject row=new JsonObject();
          row.addProperty("turnike_id", turnikeId);
          row.addProperty("sayac", sayac);
          row.addProperty("guncelleme_zamani", "2002-03-12 06:05:32.474003");
          row.addProperty("istasyon", station);
          rows.add(row);
          streamMessage.add("rows", rows); 
    	//String jsonMessage="\{\"version\": 1,\"stream\":\"TurnikeStream\",\"flags\": 0,\"rows\":[\{\"kartId\":\"2434", \"counter\" :5 \"\"\}]\}";
         // System.out.println(streamMessage.toString());
                 
          return  streamMessage.toString();
    }
}