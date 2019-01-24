package dto;

public class DTOStavkaKalkulacije {
    private int idKalkulacije, idProizvoda, kolicina, pdv;
    private double fakturnaCijena, rabat, marza;
    private String jedinicaMjere;

    public DTOStavkaKalkulacije(int idKalkulacije, int idProizvoda, double fakturnaCijena, int kolicina, String jedinicaMjere, double rabat, double marza, int pdv) {
        this.idKalkulacije = idKalkulacije;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.rabat = rabat;
        this.marza = marza;
        this.pdv = pdv;
        this.fakturnaCijena = fakturnaCijena;
        this.jedinicaMjere = jedinicaMjere;
    }

    public int getIdKalkulacije() {
        return idKalkulacije;
    }

    public void setIdKalkulacije(int idKalkulacije) {
        this.idKalkulacije = idKalkulacije;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public double getMarza() {
        return marza;
    }

    public void setMarza(double marza) {
        this.marza = marza;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public double getFakturnaCijena() {
        return fakturnaCijena;
    }

    public void setFakturnaCijena(double fakturnaCijena) {
        this.fakturnaCijena = fakturnaCijena;
    }

    public String getJedinicaMjere() {
        return jedinicaMjere;
    }

    public void setJedinicaMjere(String jedinicaMjere) {
        this.jedinicaMjere = jedinicaMjere;
    }
    
    
}
