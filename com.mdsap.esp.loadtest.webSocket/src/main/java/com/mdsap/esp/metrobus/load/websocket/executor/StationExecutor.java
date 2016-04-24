package com.mdsap.esp.metrobus.load.websocket.executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mdsap.esp.metrobus.load.websocket.client.SimpleEchoSocket;
import com.mdsap.esp.metrobus.load.websocket.model.Station;
import com.mdsap.esp.metrobus.load.websocket.worker.WorkerThread;

public class StationExecutor {
	private Station station;
	private CountDownLatch countDownLatch;
	private ExecutorService stationEx;
    private SimpleEchoSocket socket;
    
	public StationExecutor(Station station, SimpleEchoSocket socket) {
		// TODO Auto-generated constructor stub
		this.station = station;
		countDownLatch = new CountDownLatch(station.getTurnstileCount());
		this.socket=socket;
	}

	public void start() {
		stationEx = Executors.newFixedThreadPool(station.getTurnstileCount());

		for (int i = 0; i < station.getTurnstileCount(); i++) {
			stationEx.execute(new WorkerThread(countDownLatch, station, i,socket));
		}
	}

	public void await() {
		try {
			countDownLatch.await();

		} catch (InterruptedException exc) {
			System.out.println(exc);
		}
		stationEx.shutdown();
		System.out.println("Done for station..:"+station.getStationName());
	}

}
