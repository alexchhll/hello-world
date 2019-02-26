package sessionsBeans;

import entites.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class GestionCatalogue implements GestionCatalogueLocal {
    @PersistenceContext(unitName = "magasinEJB-ejbPU")
    private EntityManager em;

    @Override
    public List<Produit> selectAllProduits(){
        System.out.println("---->>>dans GestionCatalogue.selectAllProduits()");
        TypedQuery<Produit> qr = 
                em.createNamedQuery("entites.Produit.findAll", Produit.class);
        List<Produit> produits = qr.getResultList();
        return produits;
    }
    
    @Override
    public Produit selectProduitbyRef(String ref){
        if(ref != null){
            ref = ref.trim();
        }else{
            ref ="";
        }
        return em.find(Produit.class, ref);        
    }

    
}
