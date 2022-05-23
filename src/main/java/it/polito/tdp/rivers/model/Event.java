package it.polito.tdp.rivers.model;




public class Event implements Comparable<Event>{
	
	
	
	private int time;
	private double flusso_in;
	private double flusso_out;
	private double quantitaC; //quantita di acqua
	private River river;
	
	
	
	public Event(int time, double flusso_in, double flusso_out, double C,River r) {
		super();
		this.time = time;
		this.flusso_in = flusso_in;
		this.flusso_out = flusso_out;
		this.quantitaC = C;
		this.river = r;
		
	}


	@Override
	public int compareTo(Event o) {
		return this.time - o.time;
	}


	public int getTime() {
		return time;
	}


	


	public double getFlusso_in() {
		return flusso_in;
	}


	public double getFlusso_out() {
		return flusso_out;
	}


	public double getQuantitaC() {
		return quantitaC;
	}



	public void setTime(int time) {
		this.time = time;
	}



	public void setFlusso_in(double flusso_in) {
		this.flusso_in = flusso_in;
	}


	public void setFlusso_out(double flusso_out) {
		this.flusso_out = flusso_out;
	}


	public void setQuantitaC(double quantitaC) {
		this.quantitaC = quantitaC;
	}


	public River getRiver() {
		return river;
	}


	public void setRiver(River river) {
		this.river = river;
	}


	@Override
	public String toString() {
		return "Event [time=" + time + ", flusso_in=" + flusso_in + ", flusso_out=" + flusso_out + ", quantitaC="
				+ quantitaC + ", river=" + river.getName() + "]" + "\n";
	}


	
	

}
