/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author ovidiugiorgi
 */
public class Team {
    private ArrayList<Student> studenti;
    
    public Team(ArrayList<Student> studenti) {
        this.studenti = new ArrayList<>(studenti.size());
        studenti.forEach((student) -> {
            this.studenti.add(student);
        });
    }
    
    public ArrayList<Student> getStudenti() {
        return this.studenti;
    }
}
