package dto;

public class DTOStavka {

    private int idStavke, idProizvoda, kolicina, idRacuna;
    private double cijena;

    public DTOStavka(int idStavke, int idRacuna, int kolicina, double cijena, int idProizvoda) {
        this.idStavke = idStavke;
        this.idRacuna = idRacuna;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
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

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    @Override
    public String toString() {
        return "DTOStavka{" + ", idStavke=" + idStavke
                + ", idProizvoda=" + idProizvoda
                + ", kolicina=" + kolicina
                + ", cijena=" + cijena + '}';
    }

}
