package tabele;

public class TabelaProizvod {
    private Integer id, kolicina;
    private String barkod, sifra, naziv;
    private Double cijena;

    public TabelaProizvod(Integer id, String barkod, String sifra, String naziv,Integer kolicina, Double cijena) {
        this.id = id;
        this.kolicina = kolicina;
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.cijena = cijena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
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

    public Double getCijena() {
        return cijena;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }
    
}
