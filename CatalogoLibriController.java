/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giuseppevitolo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 *
 * @author giuseppe
 */
public class CatalogoLibriController implements Initializable {
    
    private Label label;
    @FXML
    private TextField iniziodate;
    @FXML
    private TextField finedate;
    @FXML
    private TextField limit;
    @FXML
    private Button aggbutt;
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private TableView<Libro> view;
    @FXML
    private MenuItem exportmenu;
    @FXML
    private TableColumn<Libro, String> isbncl;
    @FXML
    private TableColumn<Libro, String> codvolcl;
    @FXML
    private TableColumn<Libro, String> titolocl;
    @FXML
    private TableColumn<Libro, String> annocl;
    @FXML
    private TableColumn<Libro, Double> prezzocl;
    @FXML
    private TableColumn<Libro, String> pesocl;
    @FXML
    private TableColumn<Libro, String> paginecl;
    @FXML
    private ComboBox<String> combobox;
    
    private String url;
    private ObservableList<Libro> libri;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.url="https://raw.githubusercontent.com/DioGodDiamond/csv/main/Cat_Zani_ext.csv";
        libri=FXCollections.observableArrayList();
       isbncl.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
       codvolcl.setCellValueFactory(new PropertyValueFactory<>("CodVol"));
       titolocl.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
       annocl.setCellValueFactory(new PropertyValueFactory<>("Anno"));
       prezzocl.setCellValueFactory(new PropertyValueFactory<>("Prezzo"));
       pesocl.setCellValueFactory(new PropertyValueFactory<>("Peso"));
       paginecl.setCellValueFactory(new PropertyValueFactory<>("Pagine"));
       view.setItems(libri);
       view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       
       CaricaCatalogoService service=new CaricaCatalogoService(this.url,3627,"",1965,2022);
       indicator.progressProperty().bind(service.progressProperty());
       indicator.visibleProperty().bind(service.runningProperty());
       view.itemsProperty().bind(service.valueProperty());
       service.start();
       service.setOnSucceeded(e->{
            libri=service.getValue();
            ObservableList<String> tipiVol=FXCollections.observableArrayList();
            for(Libro l:libri){
                if(!tipiVol.contains(l.getTipoVol()))
                    tipiVol.add(l.getTipoVol());
            }
            combobox.setItems(tipiVol);
       });
    }    

    @FXML
    private void add(ActionEvent event) {
        int limite;
        int annoMin;
        int annoMax;
        String tipoVol;
        if(limit.getText().equals("")){
            limite=100;
        }else {
            limite=Integer.parseInt(limit.getText());
        }
        if(iniziodate.getText().equals("")){
            annoMin=1965;
        }else {
            annoMin=Integer.parseInt(iniziodate.getText());
        }
       if(finedate.getText().equals("")){
            annoMax=2022;
       }else {
            annoMax=Integer.parseInt(finedate.getText());
       }
       if(combobox.getValue()==null){
            tipoVol="";
       }else {
            tipoVol=combobox.getValue();
       }
       CaricaCatalogoService service=new CaricaCatalogoService(url,limite,tipoVol,annoMin,annoMax);
        indicator.progressProperty().bind(service.progressProperty());
        indicator.visibleProperty().bind(service.runningProperty());
        view.itemsProperty().bind(service.valueProperty());
        service.start();
    }
    
    @FXML
    private void export(ActionEvent event) throws IOException {
        List<Libro> selectedBooks=view.getSelectionModel().getSelectedItems();
        FileChooser fc=new FileChooser();
        File file=fc.showSaveDialog(null);
        if(file!=null){
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
                for(Libro l:selectedBooks){
                    bw.append(l.getIsbn()+";");
                    bw.append(l.getTitolo()+";");
                    bw.append(l.getAnno()+";");
                    bw.append(l.getPrezzo()+"\n");
                }
            }
        }
    }
}
