
package sessionsBeans;

import entites.Produit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionCatalogueLocal {

    public List<Produit> selectAllProduits();

    public Produit selectProduitbyRef(String ref);
    
}
