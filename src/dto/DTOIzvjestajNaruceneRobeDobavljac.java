/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class DTOIzvjestajNaruceneRobeDobavljac {
    private String JIBDobavljaca;
    private String nazivDobavljaca;
    private String proizvodSifra;
    private String proizvodBarkod;
    private String proizvodNaziv;
    private int kolicina;
    private double cijenaProizvoda;

    public DTOIzvjestajNaruceneRobeDobavljac(String JIBDobavljaca, String nazivDobavljaca, String proizvodSifra, String proizvodBarkod, String proizvodNaziv, int kolicina, double cijenaProizvoda) {
        this.JIBDobavljaca = JIBDobavljaca;
        this.nazivDobavljaca = nazivDobavljaca;
        this.proizvodSifra = proizvodSifra;
        this.proizvodBarkod = proizvodBarkod;
        this.proizvodNaziv = proizvodNaziv;
        this.kolicina = kolicina;
        this.cijenaProizvoda = cijenaProizvoda;
    }

    public String getJIBDobavljaca() {
        return JIBDobavljaca;
    }

    public void setJIBDobavljaca(String JIBDobavljaca) {
        this.JIBDobavljaca = JIBDobavljaca;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public String getProizvodSifra() {
        return proizvodSifra;
    }

    public void setProizvodSifra(String proizvodSifra) {
        this.proizvodSifra = proizvodSifra;
    }

    public String getProizvodBarkod() {
        return proizvodBarkod;
    }

    public void setProizvodBarkod(String proizvodBarkod) {
        this.proizvodBarkod = proizvodBarkod;
    }

    public String getProizvodNaziv() {
        return proizvodNaziv;
    }

    public void setProizvodNaziv(String proizvodNaziv) {
        this.proizvodNaziv = proizvodNaziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCijenaProizvoda() {
        return cijenaProizvoda;
    }

    public void setCijenaProizvoda(double cijenaProizvoda) {
        this.cijenaProizvoda = cijenaProizvoda;
    }
    
    
}
