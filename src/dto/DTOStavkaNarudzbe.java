package dto;

public class DTOStavkaNarudzbe {
    private int idStavkeNarudzbe, idNarudzbenice, kolicina, idProizvoda;
    private double cijena, ukupnaCijena;

    public DTOStavkaNarudzbe(int idStavkeNarudzbe, int idNarudzbenice, int kolicina, int idProizvoda, double cijena, double ukupnaCijena) {
        this.idStavkeNarudzbe = idStavkeNarudzbe;
        this.idNarudzbenice = idNarudzbenice;
        this.kolicina = kolicina;
        this.idProizvoda = idProizvoda;
        this.cijena = cijena;
        this.ukupnaCijena = ukupnaCijena;
    }

    public void setIdStavkeNarudzbe(int idStavkeNarudzbe) {
        this.idStavkeNarudzbe = idStavkeNarudzbe;
    }

    public void setIdNarudzbenice(int idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public int getIdStavkeNarudzbe() {
        return idStavkeNarudzbe;
    }

    public int getIdNarudzbenice() {
        return idNarudzbenice;
    }

    public int getKolicina() {
        return kolicina;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public double getCijena() {
        return cijena;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }
    
    
}
