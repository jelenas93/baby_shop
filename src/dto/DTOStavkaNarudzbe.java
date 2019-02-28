package dto;

public class DTOStavkaNarudzbe {
    private int idStavkeNarudzbe, idNarudzbenice, kolicina, idProizvoda;
    private double cijena;

    public DTOStavkaNarudzbe(int idStavkeNarudzbe, int idNarudzbenice, int kolicina, double cijena, int idProizvoda) {
        this.idStavkeNarudzbe = idStavkeNarudzbe;
        this.idNarudzbenice = idNarudzbenice;
        this.kolicina = kolicina;
        this.idProizvoda = idProizvoda;
        this.cijena = cijena;
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

    @Override
    public String toString() {
        return "DTOStavkaNarudzbe{" + "idStavkeNarudzbe=" + idStavkeNarudzbe + ", idNarudzbenice=" + idNarudzbenice + ", kolicina=" + kolicina + ", idProizvoda=" + idProizvoda + ", cijena=" + cijena + '}';
    }
    
    
    
}
