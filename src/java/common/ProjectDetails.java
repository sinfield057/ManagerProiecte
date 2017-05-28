/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author ovidiugiorgi
 */
public class ProjectDetails {
    private String titlu;
    private String descriere;
    private int nr_max_studenti;
    
    public ProjectDetails(String titlu, String descriere, int nr_max_studenti) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.nr_max_studenti = nr_max_studenti;
    }
    
    public String getTitlu() {
        return this.titlu;
    }
    
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }
    
    public String getDescriere() {
        return this.descriere;
    }
    
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    
    public int getNrMaxStudenti() {
        return this.nr_max_studenti;
    }
    
    public void setNrMaxStudenti(int nr_max_studenti) {
        this.nr_max_studenti = nr_max_studenti;
    }
}
