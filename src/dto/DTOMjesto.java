package dto;

public class DTOMjesto {
    private int postanskiBroj;
    private String naziv, opstina, drzava;

    public DTOMjesto(int postanskiBroj, String naziv, String opstina, String drzava) {
        this.postanskiBroj = postanskiBroj;
        this.naziv = naziv;
        this.opstina = opstina;
        this.drzava = drzava;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpstina() {
        return opstina;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }
    
    
}
