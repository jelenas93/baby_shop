package dto;

public class DTOZaposleni {
    private String ime, prezime, JMBG;
    private double iznosPlate;
    private int idZaposlenog, postanskiBroj, idTipZaposlenog;

    public DTOZaposleni(String ime, String prezime, String JMBG, double iznosPlate, int idZaposlenog, int postanskiBroj, int idTipZaposlenog) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.iznosPlate = iznosPlate;
        this.idZaposlenog = idZaposlenog;
        this.postanskiBroj = postanskiBroj;
        this.idTipZaposlenog = idTipZaposlenog;
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

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setIdTipZaposlenog(int idTipZaposlenog) {
        this.idTipZaposlenog = idTipZaposlenog;
    }
     
    
}
 