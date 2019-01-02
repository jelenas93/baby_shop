package dto;

public class DTOProizvodGrupa {
    private int idGrupe;
    private String nazivTipaProizvoda;
    private boolean pol, uzrast, duzina, sirina, visina, boja, velicina, godisnjeDoba; //dimenzije da li su dimenzije il ih rasclanim

    public DTOProizvodGrupa(int idGrupe, String nazivTipaProizvoda, boolean duzina, boolean sirina, boolean visina,boolean velicina, boolean uzrast, boolean pol, boolean boja, boolean godisnjeDoba) {
        this.idGrupe = idGrupe;
        this.nazivTipaProizvoda=nazivTipaProizvoda;
        this.pol = pol;
        this.uzrast = uzrast;
        this.boja = boja;
        this.velicina = velicina;
        this.duzina=duzina;
        this.sirina=sirina;
        this.visina=visina;
        this.godisnjeDoba=godisnjeDoba;
       
    }

    public String getNazivTipaProizvoda() {
        return nazivTipaProizvoda;
    }

    public void setNazivTipaProizvoda(String nazivTipaProizvoda) {
        this.nazivTipaProizvoda = nazivTipaProizvoda;
    }
    

    public void setIdGrupe(int idGrupe) {
        this.idGrupe = idGrupe;
    }

    public void setPol(boolean pol) {
        this.pol = pol;
    }

    public void setUzrast(boolean uzrast) {
        this.uzrast = uzrast;
    }

    public void setBoja(boolean boja) {
        this.boja = boja;
    }

    public void setVelicina(boolean velicina) {
        this.velicina = velicina;
    }

    public int getIdGrupe() {
        return idGrupe;
    }

    public boolean isPol() {
        return pol;
    }

    public boolean isUzrast() {
        return uzrast;
    }

    public boolean isBoja() {
        return boja;
    }

    public boolean isVelicina() {
        return velicina;
    }

    public boolean isDuzina() {
        return duzina;
    }

    public void setDuzina(boolean duzina) {
        this.duzina = duzina;
    }

    public boolean isSirina() {
        return sirina;
    }

    public void setSirina(boolean sirina) {
        this.sirina = sirina;
    }

    public boolean isVisina() {
        return visina;
    }

    public void setVisina(boolean visina) {
        this.visina = visina;
    }

    public boolean isGodisnjeDoba() {
        return godisnjeDoba;
    }

    public void setGodisnjeDoba(boolean godisnjeDoba) {
        this.godisnjeDoba = godisnjeDoba;
    }
    
    
    
    
}
