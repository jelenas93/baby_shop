package dto;

public class DTOProizvod {
    
    private int idProizvoda, kolicina, idKalkulacije, idSkladista, idGrupe;
    private String barkod, sifra, naziv, opis, proizvodjac, JIBProizvodjaca, tipProizvoda;
    private double cijena;
    //osobine
    private char pol;
    private int uzrast, velicina;
    private String boja;
    private double duzina, sirina, visina;

    public DTOProizvod(int idProizvoda, int kolicina, int idKalkulacije, int idSkladista, int idGrupe, String barkod, String sifra, String naziv, String opis, String proizvodjac, String JIBProizvodjaca, String tipProizvoda, double cijena, char pol, int uzrast, int velicina, String boja, double duzina, double sirina, double visina) {
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.idKalkulacije = idKalkulacije;
        this.idSkladista = idSkladista;
        this.idGrupe = idGrupe;
        this.barkod = barkod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.opis = opis;
        this.proizvodjac = proizvodjac;
        this.JIBProizvodjaca = JIBProizvodjaca;
        this.tipProizvoda = tipProizvoda;
        this.cijena = cijena;
        this.pol = pol;
        this.uzrast = uzrast;
        this.velicina = velicina;
        this.boja = boja;
        this.duzina = duzina;
        this.sirina = sirina;
        this.visina = visina;
    }

    public String getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(String tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setIdKalkulacije(int idKalkulacije) {
        this.idKalkulacije = idKalkulacije;
    }

    public void setIdSkladista(int idSkladista) {
        this.idSkladista = idSkladista;
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

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
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

    public int getIdKalkulacije() {
        return idKalkulacije;
    }

    public int getIdSkladista() {
        return idSkladista;
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

    public String getOpis() {
        return opis;
    }

    public String getProizvodjac() {
        return proizvodjac;
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
    
    
    
    
}
