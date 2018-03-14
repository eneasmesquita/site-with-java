/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author eneas
 */
@ManagedBean
@ViewScoped
public class ViewBean {

    private List<String> images;

    private MapModel markerModel;
    private Marker mkr;
    
    private boolean exibePnlDesktop = false;
    private boolean exibePnlNotebook = true;
    private boolean exibePnlXerox = false;

    @PostConstruct
    public void init() {

        //Conteudo da Galeria
        images = new ArrayList<String>();
        images.add("fachada.jpg");
        images.add("honton.jpg");
        images.add("laboratorio.jpg");

        //Conteudo do Google Maps em Localizacao
        LatLng bairroLiberdade = new LatLng(0.000000, -00.000000); //sigiloso
        LatLng bairroCentro = new LatLng(0.0000000, -00.000000); //sigiloso
        markerModel = new DefaultMapModel();
        markerModel.addOverlay(new Marker(bairroLiberdade, "Sigilo", "fachada.jpg", null, "Sigiloso"));
        markerModel.addOverlay(new Marker(bairroCentro, "Sigiloso", "fachada_centro.jpg", null, "Sigiloso"));
    }

    public ViewBean(){
    }
    
    public List<String> getImages() {
        return images;
    }

    public MapModel getMarkerModel() {
        return markerModel;
    }

    public Marker getMkr() {
        return mkr;
    }

    public void selectMarker(OverlaySelectEvent event) {
        this.mkr = (Marker) event.getOverlay();
    }
    
    public boolean isExibePnlDesktop() {
        return exibePnlDesktop;
    }

    public void setExibePnlDesktop(boolean exibePnlDesktop) {
        this.exibePnlDesktop = exibePnlDesktop;
    }

    public boolean isExibePnlNotebook() {
        return exibePnlNotebook;
    }

    public void setExibePnlNotebook(boolean exibePnlNotebook) {
        this.exibePnlNotebook = exibePnlNotebook;
    }

    public boolean isExibePnlXerox() {
        return exibePnlXerox;
    }

    public void setExibePnlXerox(boolean exibePnlXerox) {
        this.exibePnlXerox = exibePnlXerox;
    }
    
    public void reparoDesktop(){
        exibePnlDesktop = true;
        exibePnlNotebook = false;
        exibePnlXerox = false;
    }
    
    public void reparoNotebook(){
        exibePnlDesktop = false;
        exibePnlNotebook = true;
        exibePnlXerox = false;
    }
    
    public void xerox(){
        exibePnlDesktop = false;
        exibePnlNotebook = false;
        exibePnlXerox = true;
    }
}
