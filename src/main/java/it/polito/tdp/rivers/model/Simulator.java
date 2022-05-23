package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;


public class Simulator {

	
	//Modello 
	private List<Flow> flussi;
	//Costanti
	public double F_MED = 0;
	public double Q = 0;
	public double PROBABILITA = 0.05;
	int erogMin = 0;
	double quantitaFin = 0;
	
	RiversDAO dao = new RiversDAO();
	
	//Coda degli eventi 
	
	private PriorityQueue<Event> queue;
	
	public double getFMED(int idFiume) {
		
		
		for(Flow f : dao.getAllMisurazioni(idFiume))
			 this.F_MED = f.getAverageFlow();
		
		return this.F_MED * 86400;
		
	}
	
	public double getQ(int k, int idFiume) {
		
		this.Q = k * this.getFMED(idFiume) * (30);
		
		return this.Q;
	}
	
	
	
	public void creaEventi(River river, int k) {     
		
		this.getFMED(river.getId());
		this.getQ(k, river.getId());
		int time = 0;
		double quantitaC = this.getQ(k, river.getId()) / 2;
		double f_out = 0;
		
		
		this.queue = new PriorityQueue<Event>(); 
		
		
		for(int i = 0; i < dao.getFlussi(river.getName()).size(); i++) {
			
							// **** ALGORITMO *****
				  // ***  1. GESTISCO LA PROBABILITA ***
				if(Math.random() <= PROBABILITA ) {
					f_out = 8 *this.getFMED(river.getId()) ;
			}
				else {
					f_out = 0.8 *this.getFMED(river.getId()) + Math.random() ;
			}
				
				double f_in = dao.getFlussi(river.getName()).get(i).getFlusso() * 86400;
				quantitaC += f_in - f_out;
				
			    if(quantitaC < 0) {
			    	quantitaC = 0;
			    	erogMin++;
			    }
			//****  2. INSERISCO TUTTI GLI ALTRI EVENTI DA TIME = 1 NELLA QUEUE ****
			time++;
			this.queue.add(new Event(time,f_in,f_out,quantitaC,river));
		    
			if(time == queue.size())
				this.quantitaFin = quantitaC;
		
		}
		
		 
	}
	
	public int getEccezioni() {
		
		return this.erogMin;
	}
	public double calcolaMedia() {
		
		return this.quantitaFin / this.queue.size();
		
	}

	
	
//	public void run() {
//		
//		
//		while(!queue.isEmpty()) {
//			Event e = queue.poll();
//			processaEvento(e);
//		}
//		  System.out.print(this.queue);
//	}
//	
//	public void processaEvento(Event e) {
//		
//			int count =0;
//			double nuovaC = 0;
//			for(int i = 1; i < dao.getFlussi(e.getRiver().getName()).size(); i++) {
//				 
//			if(e.getQuantitaC() < 0){
//					e.setQuantitaC(0);
//					nuovaC += e.getFlusso_in() - e.getFlusso_out();
//					e.setQuantitaC(nuovaC);
//					 count++;
//				}
//				
//				Event evento = new Event(e.getTime(),e.getFlusso_in(),e.getFlusso_out(),e.getQuantitaC(),e.getRiver());
//				this.queue.add(evento);
//				
//		}
//			
//			
//			
////				f.setAverageQuantita(e.getQuantitaC() / this.queue.size());
////				f.setErogazioneMin(count);
//				
//				
//				
//				
//	}
	
	
	
	
}
