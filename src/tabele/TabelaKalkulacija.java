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
public class TabelaKalkulacija {
    
   private int  redniBroj,kolicina;
   private String sifra, naziv,jedMjere,barKod;
   private double fakturnaCijena,rabat,fakturnaVrijednostNaRabat, nabavnaCijena,nabavnaVrijednost,marza,marzaIznos,vrijednostBezPdv,stopa,pdv,prodajnaVrijednost,prodajnaCijena;
    
    public TabelaKalkulacija() {} 
    
    public TabelaKalkulacija( int rb, String barKod, String sifra,String naziv, int kol,String jedMjere, double fakturnaCijena, 
            double rabat,double fakturnaVrijednostNaRabat, double nabavnaCijena, double nabavnaVrijednost, double marza, 
            double marzaIznos, double vrijednostBezPdv, double stopa, double pdv, double prodajnaVrijednost, double prodajnaCijena) 
    
    {
    this.redniBroj = rb;
    this.barKod=barKod;
    this.fakturnaCijena=fakturnaCijena;
    this.fakturnaVrijednostNaRabat=fakturnaVrijednostNaRabat;
    this.sifra=sifra;
    this.naziv=naziv;
    this.kolicina=kol;
    this.jedMjere=jedMjere;
    this.rabat=rabat;
    this.marza=marza;
    this.marzaIznos=marzaIznos;
    this.nabavnaVrijednost=nabavnaVrijednost;
    this.nabavnaCijena=nabavnaCijena;
    this.vrijednostBezPdv=vrijednostBezPdv;
    this.stopa=stopa;
    this.pdv=pdv;
    this.prodajnaCijena=prodajnaCijena;
    this.prodajnaVrijednost=prodajnaVrijednost;
    
    
   
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
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

    public String getJedMjere() {
        return jedMjere;
    }

    public void setJedMjere(String jedMjere) {
        this.jedMjere = jedMjere;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
    }

    public double getFakturnaCijena() {
        return fakturnaCijena;
    }

    public void setFakturnaCijena(double fakturnaCijena) {
        this.fakturnaCijena = fakturnaCijena;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public double getFakturnaVrijednostNaRabat() {
        return fakturnaVrijednostNaRabat;
    }

    public void setFakturnaVrijednostNaRabat(double fakturnaVrijednostNaRabat) {
        this.fakturnaVrijednostNaRabat = fakturnaVrijednostNaRabat;
    }

    public double getNabavnaCijena() {
        return nabavnaCijena;
    }

    public void setNabavnaCijena(double nabavnaCijena) {
        this.nabavnaCijena = nabavnaCijena;
    }

    public double getNabavnaVrijednost() {
        return nabavnaVrijednost;
    }

    public void setNabavnaVrijednost(double nabavnaVrijednost) {
        this.nabavnaVrijednost = nabavnaVrijednost;
    }

    public double getMarza() {
        return marza;
    }

    public void setMarza(double marza) {
        this.marza = marza;
    }

    public double getMarzaIznos() {
        return marzaIznos;
    }

    public void setMarzaIznos(double marzaIznos) {
        this.marzaIznos = marzaIznos;
    }

    public double getVrijednostBezPdv() {
        return vrijednostBezPdv;
    }

    public void setVrijednostBezPdv(double vrijednostBezPdv) {
        this.vrijednostBezPdv = vrijednostBezPdv;
    }

    public double getStopa() {
        return stopa;
    }

    public void setStopa(double stopa) {
        this.stopa = stopa;
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public double getProdajnaVrijednost() {
        return prodajnaVrijednost;
    }

    public void setProdajnaVrijednost(double prodajnaVrijednost) {
        this.prodajnaVrijednost = prodajnaVrijednost;
    }

    public double getProdajnaCijena() {
        return prodajnaCijena;
    }

    public void setProdajnaCijena(double prodajnaCijena) {
        this.prodajnaCijena = prodajnaCijena;
    }
    
}
