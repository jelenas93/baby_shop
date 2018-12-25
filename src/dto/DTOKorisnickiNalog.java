package dto;

public class DTOKorisnickiNalog {
        private String korisnickoIme, lozinka, tipKorisnika;
        private int idZaposlenog, idNaloga;
        private boolean aktivan;

    public DTOKorisnickiNalog(String korisnickoIme, String lozinka, String tipKorisnika, int idZaposlenog, int idNaloga, boolean aktivan) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tipKorisnika = tipKorisnika;
        this.idZaposlenog = idZaposlenog;
        this.idNaloga = idNaloga;
        this.aktivan = aktivan;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public int getIdZaposlenog() {
        return idZaposlenog;
    }

    public void setIdZaposlenog(int idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
    }

    public int getIdNaloga() {
        return idNaloga;
    }

    public void setIdNaloga(int idNaloga) {
        this.idNaloga = idNaloga;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
        
        
}
