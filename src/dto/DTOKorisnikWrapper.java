/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dao.DAOKorisnickiNalog;
import dao.DAOZaposleni;

/**
 *
 * @author Tijana Lakic
 */
public class DTOKorisnikWrapper {

    private DTOKorisnickiNalog korisnickiNalog;
    private DTOZaposleni zaposleni;
    

    public DTOKorisnikWrapper() {

    }


    public DTOKorisnikWrapper(DTOKorisnickiNalog korisnickiNalog, DTOZaposleni zaposleni) {
        this.korisnickiNalog = korisnickiNalog;
        this.zaposleni = zaposleni;
    }


    public String getIme() {
        return zaposleni.getIme();
    }

    public void setIme(String ime) {
        zaposleni.setIme(ime);
    }

    public String getPrezime() {
      return zaposleni.getPrezime();

    }

    public void setPrezime(String prezime) {
        zaposleni.setPrezime(prezime);
    }

    public String getJMBG() {
        return zaposleni.getJMBG();
    }

    public void setJMBG(String JMBG) {
        zaposleni.setJMBG(JMBG);
    }

    public String getKorisnickoIme() {
        return korisnickiNalog.getKorisnickoIme();
    }

    public void setKorisnickoIme(String korisnickoIme) {
       korisnickiNalog.setKorisnickoIme(korisnickoIme);   
    }

    public String getTipKorisnika() {
        return korisnickiNalog.getTipKorisnika();
    }

    public void setTipKorisnika(String tipKorisnika) {
    korisnickiNalog.setTipKorisnika(tipKorisnika);    }

    public boolean isAktivan() {
        return korisnickiNalog.isAktivan();
    }

    public void setAktivan(boolean aktivan) {
korisnickiNalog.setAktivan(aktivan);    }

    public int getIdZaposlenog() {
        return zaposleni.getIdZaposlenog();
    }

    public void setIdZaposlenog(int idZaposlenog) {
zaposleni.setIdTipZaposlenog(idZaposlenog);    }

    public int getIdNaloga() {
        return korisnickiNalog.getIdNaloga();
    }

    public void setIdNaloga(int idNaloga) {
korisnickiNalog.setIdNaloga(idNaloga);    }
   

}
