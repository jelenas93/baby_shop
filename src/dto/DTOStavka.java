package dto;

public class DTOStavka {
    
    private int idRacuna, idStavke, idProizvoda, kolicina;
    private String jedinicaMjere; 
    private double cijena;

    public DTOStavka(int idRacuna, int idStavke, int idProizvoda, int kolicina, String jedinicaMjere, double cijena) {
        this.idRacuna = idRacuna;
        this.idStavke = idStavke;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.jedinicaMjere = jedinicaMjere;
        this.cijena = cijena;
    }

    public int getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(int idRacuna) {
        this.idRacuna = idRacuna;
    }

    public int getIdStavke() {
        return idStavke;
    }

    public void setIdStavke(int idStavke) {
        this.idStavke = idStavke;
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

    public String getJedinicaMjere() {
        return jedinicaMjere;
    }

    public void setJedinicaMjere(String jedinicaMjere) {
        this.jedinicaMjere = jedinicaMjere;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    @Override
    public String toString() {
        return "DTOStavka{" + "idRacuna=" + idRacuna 
                + ", idStavke=" + idStavke 
                + ", idProizvoda=" + idProizvoda 
                + ", kolicina=" + kolicina 
                + ", jedinicaMjere=" + jedinicaMjere 
                + ", cijena=" + cijena + '}';
    }
    
    
}
