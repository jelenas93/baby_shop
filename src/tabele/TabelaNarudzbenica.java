/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

/**
 *
 * @author Jovana Trkulja
 */
public class TabelaNarudzbenica {
    
     private String sifra, naziv;
     private int naruceno;  
     private String barKod;
     
    public TabelaNarudzbenica() {} 
    
    public TabelaNarudzbenica(String sifra, String naziv, int naruceno, String barKod) { 
        
        this.sifra=sifra;
        this.naziv=naziv;
        this.naruceno= naruceno; 
        this.barKod=barKod;
    
    
    }  
     public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barkod) {
        this.barKod = barkod;
    }
    
    public int getNaruceno() {  
        
    return naruceno;
    } 
    
    public void setNaruceno ( int naruceno) {  
    
     this.naruceno=naruceno;
    }
     
      public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
}
