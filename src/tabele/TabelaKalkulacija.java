package tabele;

public class TabelaKalkulacija {

    private int kolicina, stopa, idProizvoda;
    private String sifra, naziv, jedMjere, barKod;
    private double fakturnaCijena, rabat, fakturnaVrijednostNaRabat, nabavnaCijena, nabavnaVrijednost, marza, marzaIznos, vrijednostBezPdv, pdv, prodajnaVrijednost, prodajnaCijena;

    public TabelaKalkulacija() {
    }

    public TabelaKalkulacija(String sifra, String barKod, String naziv, int kolicina, String jedMjere, int idProizvoda) {
        this.barKod = barKod;
        this.sifra = sifra;
        this.naziv = naziv;
        this.kolicina=kolicina;
        this.jedMjere=jedMjere;
        this.stopa = 17;
        this.idProizvoda=idProizvoda;
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

    public String getJedMjere() {
        return jedMjere;
    }

    public void setJedMjere(String jedMjere) {
        this.jedMjere = jedMjere;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
    }

    public double getFakturnaCijena() {
        return fakturnaCijena;
    }

    public void setFakturnaCijena(double fakturnaCijena) {
        this.fakturnaCijena = fakturnaCijena;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public double getFakturnaVrijednostNaRabat() {
        return fakturnaVrijednostNaRabat;
    }

    public void setFakturnaVrijednostNaRabat(double fakturnaVrijednostNaRabat) {
        this.fakturnaVrijednostNaRabat = fakturnaVrijednostNaRabat;
    }

    public double getNabavnaCijena() {
        return nabavnaCijena;
    }

    public void setNabavnaCijena(double nabavnaCijena) {
        this.nabavnaCijena = nabavnaCijena;
    }

    public double getNabavnaVrijednost() {
        return nabavnaVrijednost;
    }

    public void setNabavnaVrijednost(double nabavnaVrijednost) {
        this.nabavnaVrijednost = nabavnaVrijednost;
    }

    public double getMarza() {
        return marza;
    }

    public void setMarza(double marza) {
        this.marza = marza;
    }

    public double getMarzaIznos() {
        return marzaIznos;
    }

    public void setMarzaIznos(double marzaIznos) {
        this.marzaIznos = marzaIznos;
    }

    public double getVrijednostBezPdv() {
        return vrijednostBezPdv;
    }

    public void setVrijednostBezPdv(double vrijednostBezPdv) {
        this.vrijednostBezPdv = vrijednostBezPdv;
    }

    public int getStopa() {
        return stopa;
    }

    public void setStopa(int stopa) {
        this.stopa = stopa;
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public double getProdajnaVrijednost() {
        return prodajnaVrijednost;
    }

    public void setProdajnaVrijednost(double prodajnaVrijednost) {
        this.prodajnaVrijednost = prodajnaVrijednost;
    }

    public double getProdajnaCijena() {
        return prodajnaCijena;
    }

    public void setProdajnaCijena(double prodajnaCijena) {
        this.prodajnaCijena = prodajnaCijena;
    }

}
