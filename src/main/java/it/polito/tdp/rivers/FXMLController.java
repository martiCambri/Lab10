/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private RiversDAO dao = new RiversDAO();
	private Simulator sim = new Simulator();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    
    @FXML
    void dammiLaTuaVita(ActionEvent event) {
    	
     this.txtEndDate.clear();
     this.txtStartDate.clear();
     this.txtFMed.clear();
     this.txtNumMeasurements.clear();
     
     
     
      
    	for(River r: dao.getAllRivers()) {
    		if(r.getName().compareTo(String.valueOf(this.boxRiver.getValue()))==0){ 
    			List<Flow> ultima = this.dao.getAllMisurazioni(r.getId());
    			for(Flow f: ultima) {
    				if(f.getIdRiver() == r.getId()) {
    					
    					this.txtStartDate.appendText(String.valueOf(f.getPrimoGiorno()));
    					this.txtEndDate.appendText(String.valueOf(f.getUltimoGiorno()));
    					this.txtNumMeasurements.appendText(String.valueOf(f.getMisurazioniTot()));
    					this.txtFMed.appendText(String.valueOf(f.getAverageFlow()));
    					
    				}
    			}
    		
    	}
    	
    }
    
    
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	
       for(River r: dao.getAllRivers()) {
    	   if(r.equals(this.boxRiver.getValue()))
    		   sim.creaEventi(r, Integer.parseInt(this.txtK.getText()));
       }
       
       this.txtResult.appendText("Numero di volte in cui il flusso minimo non è stato garantito: " + String.valueOf(sim.getEccezioni())+ "\n");
       this.txtResult.appendText("Capacità media bacino su fattore scala " + this.txtK.getText() +" : " +  String.valueOf(sim.calcolaMedia()));
    	
    }
    
    
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(River r: dao.getAllRivers()) {
    		this.boxRiver.getItems().add(r);
    	}
    	
    }
}
