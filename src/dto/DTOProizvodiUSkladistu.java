/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

public class DTOProizvodiUSkladistu {
    private String barkod;
    private String sifra;
    private String naziv;
    private int kolicina;
    private double cijena;

    public DTOProizvodiUSkladistu(String barkod, String sifra, String naziv, int kolicina, double cijena) {
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
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

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
    
    
}
