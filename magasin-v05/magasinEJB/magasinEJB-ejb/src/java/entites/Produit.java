package entites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries(
        {
            @NamedQuery(name = "entites.Produit.findAll", query = "select p from Produit p")
        }
)
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String ref;
    @Column(nullable = false, length = 100)
    private String nom;
    @Column(length = 500)
    private String description;
    private float prix; // prix hors taxe
    private int stock;

    public Produit() {
    }

    public Produit(String ref, String nom, float prix, int stock) {
        this.ref = ref;
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }

    public Produit(String ref, String nom, String description, float prix, int stock) {
        this.ref = ref;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ref != null ? ref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.ref == null && other.ref != null) || (this.ref != null && !this.ref.equals(other.ref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom+"[ " + ref + " ]";
    }
    
}
