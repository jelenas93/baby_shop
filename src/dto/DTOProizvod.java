package dto;

public class DTOProizvod {
    
    private int idProizvoda, idTipProizvoda, kolicina, idGrupe;
    private String barkod, sifra, naziv, JIBProizvodjaca, godisnjeDoba;
    private double cijena;
    //osobine
    private char pol;
    private int uzrast, velicina;
    private String boja;
    private double duzina, sirina, visina;

    public DTOProizvod(int idProizvoda, String barkod, String sifra, String naziv, int kolicina, double cijena, String JIBProizvodjaca, int idTipProizvoda, int idGrupe, char pol, int uzrast, String boja, int velicina, double duzina, double sirina, double visina, String godisnjeDoba) {
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.idGrupe = idGrupe;
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.idTipProizvoda=idTipProizvoda;
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

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public void setPol(char pol) {
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

    public double getCijena() {
        return cijena;
    }

    public char getPol() {
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

    public int getIdTipProizvoda() {
        return idTipProizvoda;
    }

    public void setIdTipProizvoda(int idTipProizvoda) {
        this.idTipProizvoda = idTipProizvoda;
    }

    public String getGodisnjeDoba() {
        return godisnjeDoba;
    }

    public void setGodisnjeDoba(String godisnjeDoba) {
        this.godisnjeDoba = godisnjeDoba;
    }
}
