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
public class TabelaNarudzba {
     private String barKod, sifra, naziv;
      private Double cijena;
      private int stanje;
      
      
      public TabelaNarudzba() {} 
      
      public TabelaNarudzba(String sifra, String barKod, String naziv, Double cijena ,int stanje )  
      {  
      this.sifra=sifra;
      this.barKod=barKod;
      this.naziv=naziv;
      this.cijena=cijena;
      this.stanje=stanje;
      
      
      }
      
      
      public Integer getStanje() {
        return stanje;
    }

    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }
      
      
       public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barkod) {
        this.barKod = barkod;
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

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }
      
      
}
