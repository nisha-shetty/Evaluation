package com.robosoft.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeWatch {

	private long startTime;
	private long stopTime;
	private List<Lap> laps;

	public TimeWatch() {
		laps = new ArrayList<Lap>();
	}
	/**
	 * Starts the timer
	 */
	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Stops the timer
	 */
	public void stop() {
		this.stopTime = System.currentTimeMillis();
	}

	/**
	 * Reset the timer.
	 */
	public void reset() {
		this.startTime = System.currentTimeMillis();
		this.stopTime = 0;
	}

	/**
	 * Returns time taken in Milli seconds
	 * 
	 * @return long Time taken
	 */
	public long getTimeTakenInMill() {
		return (stopTime - startTime);
	}

	/**
	 * Returns time taken in seconds
	 * 
	 * @return long Time taken
	 */
	public long getTimeTakenInSeconds() {
		return TimeUnit.MILLISECONDS.toSeconds(stopTime - startTime);
	}

	/**
	 * Returns the Start time of the timer
	 * 
	 * @return long Start time of timer
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Stop time of the timer
	 * 
	 * @return long Stop time of timer
	 */
	public long getStopTime() {
		return stopTime;
	}
	
	/**
	 * Add a lap to the timer
	 * This will record the lap time 
	 */
	public void lap(){
		this.laps.add(new Lap(System.currentTimeMillis()));
	}
	
	/**
	 * Add a lap with a nmessahe
	 * @param message : The message to add with the lap
	 */
	public void lap(String message){
		this.laps.add(new Lap(System.currentTimeMillis(), message));
	}
	
	/**
	 * Resets the Laps
	 */
	public void resetLaps(){
		this.laps.clear();
	}

	/**
	 * Returns the Latime time Board
	 * @return String
	 */
	public String getLapTimes(){
		StringBuilder str = new StringBuilder();
		int i = 0;
		if(!laps.isEmpty())
			for(Lap lap : laps){
				str.append("Lap ").append(i+1).append(" :: ").append(lap.lapStartTime).append(" :: ");
				str.append("[Lap-Time: ").append(getLapTime(i)).append("]  :: ");
				if(lap.message!= null)
					str.append(lap.message);
				str.append("\n");
				i++;
			}
		return str.toString();
	}
	
	private long getLapTime(int index){
		if(index == 0)
			return 0;
		return laps.get(index).lapStartTime - laps.get(index-1).lapStartTime;
	}
	
//	/**
//	 * Returns a list of lap start times
//	 * @return List of Long -- Lap start times
//	 */
//	public List<Lap> getLapTime(){
//		return this.laps;
//	}
	
	
	class Lap{
		private long lapStartTime;
		private String message;
		public Lap(long lapStartTime, String message) {
			this.lapStartTime = lapStartTime;
			this.message = message;
		}
		
		public Lap(long lapStartTime) {
			this.lapStartTime = lapStartTime;
			this.message = null;
		}

		public long getLapStartTime() {
			return lapStartTime;
		}
		public void setLapStartTime(long lapStartTime) {
			this.lapStartTime = lapStartTime;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
	}
	
	

}
