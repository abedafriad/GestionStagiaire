/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Abed
 */
@Entity
public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    
    @OneToMany(mappedBy = "departement")
    private List<Stagee> stagees;
    @OneToMany(mappedBy = "departement")
    private List<Encadrant> encadrants;
    @OneToMany(mappedBy = "departement")
    private List<Stagiaire> stagiaires;


    public Departement(String nom) {
        this.nom = nom;
    }

    public List<Encadrant> getEncadrants() {
        return encadrants;
    }

    public void setEncadrants(List<Encadrant> encadrants) {
        this.encadrants = encadrants;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    
    public Departement() {
    }

    public List<Stagee> getStagees() {
        return stagees;
    }

    public void setStagees(List<Stagee> stagees) {
        this.stagees = stagees;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "(" + id + ") " + nom;
    }

}
