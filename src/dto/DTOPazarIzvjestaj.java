package dto;

public class DTOPazarIzvjestaj {
    private int mjesec;
    private double iznosZaMjesec;

    public DTOPazarIzvjestaj(int mjesec, double iznosZaMjesec) {
        this.mjesec = mjesec;
        this.iznosZaMjesec = iznosZaMjesec;
    }

    public int getMjesec() {
        return mjesec;
    }

    public void setMjesec(int mjesec) {
        this.mjesec = mjesec;
    }

    public double getIznosZaMjesec() {
        return iznosZaMjesec;
    }

    public void setIznosZaMjesec(double iznosZaMjesec) {
        this.iznosZaMjesec = iznosZaMjesec;
    }
    
    
}
