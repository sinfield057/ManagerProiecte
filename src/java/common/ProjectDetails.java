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
    private int id_proiect;
    private String titlu;
    private String descriere;
    private int nr_max_studenti;
    private int nr_echipe;
    
    public ProjectDetails(
            int id_proiect, String titlu, String descriere,
            int nr_max_studenti, int nr_echipe) {
        this.id_proiect = id_proiect;
        this.titlu = titlu;
        this.descriere = descriere;
        this.nr_max_studenti = nr_max_studenti;
        this.nr_echipe = nr_echipe;
    }
    
    public int getIdProiect() {
        return this.id_proiect;
    }
    
    public void setIdProiect(int id_proiect) {
        this.id_proiect = id_proiect;
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
    
    public int getNrEchipe() {
        return this.nr_echipe;
    }
    
    public void setNrEchipe(int nr_echipe) {
        this.nr_echipe = nr_echipe;
    }
}
