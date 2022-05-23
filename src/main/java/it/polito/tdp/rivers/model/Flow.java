package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow {
	private LocalDate primoGiorno;
	private LocalDate ultimoGiorno;
	private double averageFlow;
    private int misurazioniTot;
    int idRiver;
    private double flusso;
    private double averageQuantita;
    private double erogazioneMin;
    

	public Flow(int idRiver, LocalDate primoGiorno, LocalDate ultimoGiorno,int misurazioniTot, double averageFlow ) {
		
		this.primoGiorno = primoGiorno;
		this.ultimoGiorno = ultimoGiorno;
		this.averageFlow = averageFlow;
		this.misurazioniTot = misurazioniTot;
		this.idRiver = idRiver;
		
	}
	
	public Flow(double flow) {
		
		this.flusso = flow;
	}
	public Flow(double averageQuantita, double erogazioneMin) {
		
		this.averageQuantita = averageQuantita;
		this.erogazioneMin = erogazioneMin;
	}
	

	public LocalDate getPrimoGiorno() {
		return primoGiorno;
	}

	public LocalDate getUltimoGiorno() {
		return ultimoGiorno;
	}

	public double getAverageFlow() {
		return averageFlow;
	}

	

	public void setPrimoGiorno(LocalDate primoGiorno) {
		this.primoGiorno = primoGiorno;
	}

	public void setUltimoGiorno(LocalDate ultimoGiorno) {
		this.ultimoGiorno = ultimoGiorno;
	}

	public void setAverageFlow(double averageFlow) {
		this.averageFlow = averageFlow;
	}

	
	@Override
	public String toString() {
		return "Flow [primoGiorno=" + primoGiorno + ", ultimoGiorno=" + ultimoGiorno + ", averageFlow=" + averageFlow
				+ ", misurazioniTot=" + misurazioniTot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageFlow);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((primoGiorno == null) ? 0 : primoGiorno.hashCode());
		result = prime * result + ((ultimoGiorno == null) ? 0 : ultimoGiorno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (Double.doubleToLongBits(averageFlow) != Double.doubleToLongBits(other.averageFlow))
			return false;
		if (primoGiorno == null) {
			if (other.primoGiorno != null)
				return false;
		} else if (!primoGiorno.equals(other.primoGiorno))
			return false;
		if (ultimoGiorno == null) {
			if (other.ultimoGiorno != null)
				return false;
		} else if (!ultimoGiorno.equals(other.ultimoGiorno))
			return false;
		return true;
	}

	public int getMisurazioniTot() {
		return misurazioniTot;
	}

	public void setMisurazioniTot(int misurazioniTot) {
		this.misurazioniTot = misurazioniTot;
	}

	public int getIdRiver() {
		return idRiver;
	}

	public void setIdRiver(int idRiver) {
		this.idRiver = idRiver;
	}

	public double getFlusso() {
		return flusso;
	}

	public void setFlusso(double flusso) {
		this.flusso = flusso;
	}

	public double getAverageQuantita() {
		return averageQuantita;
	}

	public double getErogazioneMin() {
		return erogazioneMin;
	}

	public void setAverageQuantita(double averageQuantita) {
		this.averageQuantita = averageQuantita;
	}

	public void setErogazioneMin(double erogazioneMin) {
		this.erogazioneMin = erogazioneMin;
	}

	
	

	
}
