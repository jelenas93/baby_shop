package tabele;

public class TabelaKasa {
    private String barkod, sifra, naziv;
    private Integer kolicina;
    private Double cijena, vrijednost;
    
    public TabelaKasa(){}

    public TabelaKasa(String barkod, String sifra, String naziv, Integer kolicina, Double cijena, Double vrijednost) {
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cijena = cijena;
        this.vrijednost = vrijednost;
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

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public Double getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Double vrijednost) {
        this.vrijednost = vrijednost;
    }

    @Override
    public String toString() {
        return "TabelaKasa{" + "barkod=" + barkod + ", sifra=" + sifra + ", naziv=" + naziv + ", kolicina=" + kolicina + ", cijena=" + cijena + ", vrijednost=" + vrijednost + '}';
    }
   
    
}
