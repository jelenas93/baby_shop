package dto;

public class DTOZaposleni {

    private String ime, prezime, JMBG, mejl;
    private double iznosPlate;
    private int idZaposlenog, postanskiBroj, idTipZaposlenog;

    public DTOZaposleni(String ime, String prezime, String JMBG, double iznosPlate,
            String mejl,int postanskiBroj,int idTipZaposlenog) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.iznosPlate = iznosPlate;
        this.mejl = mejl;
        this.idTipZaposlenog = idTipZaposlenog;
        this.postanskiBroj = postanskiBroj;
    }
      public DTOZaposleni(int idZaposlenog,String ime, String prezime, String JMBG, double iznosPlate,
            String mejl,int postanskiBroj,int idTipZaposlenog) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.iznosPlate = iznosPlate;
        this.mejl = mejl;
        this.idTipZaposlenog = idTipZaposlenog;
        this.postanskiBroj = postanskiBroj;
        this.idZaposlenog=idZaposlenog;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public double getIznosPlate() {
        return iznosPlate;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public int getIdTipZaposlenog() {
        return idTipZaposlenog;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public void setIznosPlate(double iznosPlate) {
        this.iznosPlate = iznosPlate;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setIdTipZaposlenog(int idTipZaposlenog) {
        this.idTipZaposlenog = idTipZaposlenog;
    }

  /* @Override
    public String toString() {
        return "DTOZaposleni{" + "idZaposlenog=" + idZaposlenog
                + ",Ime=" + ime
                + ", Prezime=" + prezime
                + ", JMBG=" + JMBG
                + ", Iznos Plate=" + iznosPlate
                + ", Postanski broj=" + postanskiBroj
                + ", idTipZaposlenog=" + idTipZaposlenog + '}';
    }*/

}
