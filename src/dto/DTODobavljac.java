package dto;

public class DTODobavljac {
    private int idDobavljaca, postanskiBroj;
    private String naziv, email, adresa,telefon, JIBDobavljaca;

    public DTODobavljac(int idDobavljaca, int postanskiBroj, String naziv, String email, String adresa, String telefon, String JIBDobavljaca) {
        this.idDobavljaca = idDobavljaca;
        this.postanskiBroj = postanskiBroj;
        this.naziv = naziv;
        this.email = email;
        this.adresa = adresa;
        this.telefon = telefon;
        this.JIBDobavljaca = JIBDobavljaca;
    }

    public int getIdDobavljaca() {
        return idDobavljaca;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getJIBDobavljaca() {
        return JIBDobavljaca;
    }

    public void setIdDobavljaca(int idDobavljaca) {
        this.idDobavljaca = idDobavljaca;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setJIBDobavljaca(String JIBDobavljaca) {
        this.JIBDobavljaca = JIBDobavljaca;
    }
    
    
}
