package sessionsBeans;

import java.util.Collection;
import javax.ejb.Local;
import obj.LignePanier;


@Local
public interface GestionPanierLocal {

    public void ajouter(String ref);

    public Collection<LignePanier> getMesLignesPaniers();

    public boolean isEmpty();

    public float getTotalHT();

    public int getNbArticles();
    
}
