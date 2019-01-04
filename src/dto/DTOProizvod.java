package dto;

public class DTOProizvod {
    
    private int idProizvoda, kolicina, idGrupe;
    private String barkod, sifra, naziv, JIBProizvodjaca, godisnjeDoba;
    private Double cijena;
    //osobine
    private int uzrast, velicina;
    private String pol, boja;
    private double duzina, sirina, visina;

    public DTOProizvod(int idProizvoda, String barkod, String sifra, String naziv, int kolicina, Double cijena, String JIBProizvodjaca, int idGrupe,double duzina, double sirina, double visina, int velicina, int uzrast, String pol, String boja, String godisnjeDoba) {
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.idGrupe = idGrupe;
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.JIBProizvodjaca = JIBProizvodjaca;
        this.cijena = cijena;
        this.pol = pol;
        this.uzrast = uzrast;
        this.velicina = velicina;
        this.boja = boja;
        this.duzina = duzina;
        this.sirina = sirina;
        this.visina = visina;
        this.godisnjeDoba=godisnjeDoba;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setIdGrupe(int idGrupe) {
        this.idGrupe = idGrupe;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setJIBProizvodjaca(String JIBProizvodjaca) {
        this.JIBProizvodjaca = JIBProizvodjaca;
    }

    public void setCijena(Double cijena) {
        this.cijena = cijena;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setUzrast(int uzrast) {
        this.uzrast = uzrast;
    }

    public void setVelicina(int velicina) {
        this.velicina = velicina;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public int getKolicina() {
        return kolicina;
    }
    
    public int getIdGrupe() {
        return idGrupe;
    }

    public String getBarkod() {
        return barkod;
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getJIBProizvodjaca() {
        return JIBProizvodjaca;
    }

    public Double getCijena() {
        return cijena;
    }

    public String getPol() {
        return pol;
    }

    public int getUzrast() {
        return uzrast;
    }

    public int getVelicina() {
        return velicina;
    }

    public String getBoja() {
        return boja;
    }

    public double getDuzina() {
        return duzina;
    }

    public double getSirina() {
        return sirina;
    }

    public double getVisina() {
        return visina;
    }

    public String getGodisnjeDoba() {
        return godisnjeDoba;
    }

    public void setGodisnjeDoba(String godisnjeDoba) {
        this.godisnjeDoba = godisnjeDoba;
    }
}
