/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giuseppevitolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author giuseppe
 */
public class CaricaCatalogoTask extends Task<ObservableList<Libro>>{
    private String url;
    private int limite;
    private String tipoVol;
    private int annoMin;
    private int annoMax;

    public CaricaCatalogoTask(String url, int limite, String tipoVol, int annoMin, int annoMax) {
        this.url = url;
        this.limite = limite;
        this.tipoVol = tipoVol;
        this.annoMin = annoMin;
        this.annoMax = annoMax;
    }

    @Override
    protected ObservableList<Libro> call() throws Exception {
        ObservableList<Libro> libri=FXCollections.observableArrayList();
        int i=1;
        try(Scanner s=new Scanner(new BufferedReader(new InputStreamReader(new URL(url).openStream())))){
            s.useDelimiter(";|\n");
            s.useLocale(Locale.US);
            if(s.hasNext())
                s.nextLine();
            
            while(s.hasNext() && i<limite){
                Libro l=new Libro();
                String tipoVolume=s.next();
                l.setTipoVol(tipoVolume);
                l.setGred(s.next());
                l.setIsbn(s.next());
                l.setCodVol(s.next());
                l.setTitolo(s.next());
                String anno=s.next();
                int a=Integer.parseInt(anno);
                if(65<=a && a<=99)
                    a=1900+a;
                else 
                    a=2000+a;
                l.setAnno(anno);
                l.setPrezzo(s.nextDouble());
                l.setPeso(s.next());
                l.setPagine(s.next());
                if(tipoVol.equals("")){
                    if(annoMin<=a && a<=annoMax){
                    libri.add(l);
                    i++;
                    updateProgress(i,limite);
                }
                }else if(annoMin<=a && a<=annoMax){
                    if(tipoVol.equals(tipoVolume)){
                        libri.add(l);
                        i++;
                        updateProgress(i,limite);
                    }
            }
        }
    }
  return libri;
}
}

    






















