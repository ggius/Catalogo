/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giuseppevitolo;

import java.util.Objects;

/**
 *
 * @author giuseppe
 */
public class Libro {
    private String tipoVol;
    private String gred;
    private String isbn;
    private String codVol;
    private String titolo;
    private String anno;
    private double prezzo;
    private String peso;
    private String pagine;
 
    public Libro() {
    }
    public Libro(String tipoVol, String gred, String isbn, String codVol, String titolo, String anno, double prezzo, String peso, String pagine) {
        this.tipoVol = tipoVol;
        this.gred = gred;
        this.isbn = isbn;
        this.codVol = codVol;
        this.titolo = titolo;
        this.anno = anno;
        this.prezzo = prezzo;
        this.peso = peso;
        this.pagine = pagine;
    }
 
    public String getTipoVol() {
        return tipoVol;
    }
 
    public void setTipoVol(String tipoVol) {
        this.tipoVol = tipoVol;
    }
 
    public String getGred() {
        return gred;
    }
 
    public void setGred(String gred) {
        this.gred = gred;
    }
 
    public String getIsbn() {
        return isbn;
    }
 
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
 
    public String getCodVol() {
        return codVol;
    }
 
    public void setCodVol(String codVol) {
        this.codVol = codVol;
    }
 
    public String getTitolo() {
        return titolo;
    }
 
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
 
    public String getAnno() {
        return anno;
    }
 
    public void setAnno(String anno) {
        this.anno = anno;
    }
 
    public double getPrezzo() {
        return prezzo;
    }
 
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
 
    public String getPeso() {
        return peso;
    }
 
    public void setPeso(String peso) {
        this.peso = peso;
    }
 
    public String getPagine() {
        return pagine;
    }
 
    public void setPagine(String pagine) {
        this.pagine = pagine;
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.isbn);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Libro{" + "tipoVol=" + tipoVol + ", gred=" + gred + ", isbn=" + isbn + ", codVol=" + codVol + ", titolo=" + titolo + ", anno=" + anno + ", prezzo=" + prezzo + ", peso=" + peso + ", pagine=" + pagine + '}';
    }
    
}