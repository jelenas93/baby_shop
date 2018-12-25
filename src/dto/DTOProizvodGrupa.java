package dto;

public class DTOProizvodGrupa {
    private int idGrupe;
    private boolean pol, uzrast, dimenzije,boja, velicina; //dimenzije da li su dimenzije il ih rasclanim

    public DTOProizvodGrupa(int idGrupe, boolean pol, boolean uzrast, boolean dimenzije, boolean boja, boolean velicina) {
        this.idGrupe = idGrupe;
        this.pol = pol;
        this.uzrast = uzrast;
        this.dimenzije = dimenzije;
        this.boja = boja;
        this.velicina = velicina;
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

    public void setDimenzije(boolean dimenzije) {
        this.dimenzije = dimenzije;
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

    public boolean isDimenzije() {
        return dimenzije;
    }

    public boolean isBoja() {
        return boja;
    }

    public boolean isVelicina() {
        return velicina;
    }
    
    
    
    
}
