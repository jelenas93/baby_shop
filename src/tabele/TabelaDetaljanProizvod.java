/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

public class TabelaDetaljanProizvod {
    
    private String barkod;
    private String sifra;
    private String naziv;
    private String JIBProizvodjaca;
    private int kolicina;
    private Double cijena;
    //osobine
    private double duzina, sirina, visina;
    private int velicina,uzrast ;
    private String pol, boja;
    private String godisnjeDoba;

    public TabelaDetaljanProizvod(String barkod, String sifra, String naziv, String JIBProizvodjaca, int kolicina, Double cijena, double duzina, double sirina, double visina, int velicina, int uzrast, String pol, String boja, String godisnjeDoba) {
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.JIBProizvodjaca = JIBProizvodjaca;
        this.kolicina = kolicina;
        this.cijena = cijena;
        this.duzina = duzina;
        this.sirina = sirina;
        this.visina = visina;
        this.velicina = velicina;
        this.uzrast = uzrast;
        this.pol = pol;
        this.boja = boja;
        this.godisnjeDoba = godisnjeDoba;
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

    public String getJIBProizvodjaca() {
        return JIBProizvodjaca;
    }

    public void setJIBProizvodjaca(String JIBProizvodjaca) {
        this.JIBProizvodjaca = JIBProizvodjaca;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public double getDuzina() {
        return duzina;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public int getVelicina() {
        return velicina;
    }

    public void setVelicina(int velicina) {
        this.velicina = velicina;
    }

    public int getUzrast() {
        return uzrast;
    }

    public void setUzrast(int uzrast) {
        this.uzrast = uzrast;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getGodisnjeDoba() {
        return godisnjeDoba;
    }

    public void setGodisnjeDoba(String godisnjeDoba) {
        this.godisnjeDoba = godisnjeDoba;
    }

    @Override
    public String toString() {
        return "TabelaDetaljanProizvod{" + "barkod=" + barkod + ", sifra=" + sifra + ", naziv=" + naziv + ", JIBProizvodjaca=" + JIBProizvodjaca + ", kolicina=" + kolicina + ", cijena=" + cijena + ", duzina=" + duzina + ", sirina=" + sirina + ", visina=" + visina + ", velicina=" + velicina + ", uzrast=" + uzrast + ", pol=" + pol + ", boja=" + boja + ", godisnjeDoba=" + godisnjeDoba + '}';
    }
    
    
}
