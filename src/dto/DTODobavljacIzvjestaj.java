/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class DTODobavljacIzvjestaj {
    private String JIBDobavljaca;
    private String nazivDobavaljaca;
    private String sifraProizvoda;
    private String barkodProizvoda;
    private String nazivProizvoda;
    private int kolicinaProizvoda;
    private double cijenaProizvoda;

    public DTODobavljacIzvjestaj(String JIBDobavljaca, String nazivDobavaljaca, String sifraProizvoda, String barkodProizvoda, String nazivProizvoda, int kolicinaProizvoda, double cijenaProizvoda) {
        this.JIBDobavljaca = JIBDobavljaca;
        this.nazivDobavaljaca = nazivDobavaljaca;
        this.sifraProizvoda=sifraProizvoda;
        this.barkodProizvoda = barkodProizvoda;
        this.nazivProizvoda = nazivProizvoda;
        this.kolicinaProizvoda = kolicinaProizvoda;
        this.cijenaProizvoda = cijenaProizvoda;
    }

    public String getJIBDobavljaca() {
        return JIBDobavljaca;
    }

    public void setJIBDobavljaca(String JIBDobavljaca) {
        this.JIBDobavljaca = JIBDobavljaca;
    }

    public String getNazivDobavaljaca() {
        return nazivDobavaljaca;
    }

    public void setNazivDobavaljaca(String nazivDobavaljaca) {
        this.nazivDobavaljaca = nazivDobavaljaca;
    }
    
    public String getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public String getBarkodProizvoda() {
        return barkodProizvoda;
    }

    public void setBarkodProizvoda(String barkodProizvoda) {
        this.barkodProizvoda = barkodProizvoda;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public int getKolicinaProizvoda() {
        return kolicinaProizvoda;
    }

    public void setKolicinaProizvoda(int kolicinaProizvoda) {
        this.kolicinaProizvoda = kolicinaProizvoda;
    }

    public double getCijenaProizvoda() {
        return cijenaProizvoda;
    }

    public void setCijenaProizvoda(double cijenaProizvoda) {
        this.cijenaProizvoda = cijenaProizvoda;
    }
    
}
