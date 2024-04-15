/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giuseppevitolo;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author giuseppe
 */
public class CaricaCatalogoService extends Service<ObservableList<Libro>> {

    private String url;
    private int limite;
    private String tipoVol;
    private int annoMin;
    private int annoMax;
    

    public CaricaCatalogoService(String url, int limite, String tipoVol, int annoMin, int annoMax) {
        this.url = url;
        this.limite = limite;
        this.tipoVol = tipoVol;
        this.annoMin=annoMin;
        this.annoMax=annoMax;
    }
    
    
    @Override
    protected Task<ObservableList<Libro>> createTask() {
           return new CaricaCatalogoTask(url, limite, tipoVol, annoMin, annoMax);
    }
}
