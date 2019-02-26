package sessionsBeans;

import entites.Client;
import entites.Produit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CreationDonnees implements CreationDonneesLocal {
    @PersistenceContext(unitName = "magasinEJB-ejbPU")
    private EntityManager em;

    @Override
    public void creerDonnes()throws Exception {
        
        TypedQuery<Long> qr = em.createNamedQuery("entites.Client.qteClients",Long.class);
        Long qte = qr.getSingleResult();
        
        if(qte > 0){
            throw new Exception("les données existent déjà!!");
        }
        
        Client c01 = new Client("pseudo-01", "12345678", "email.cli01@gmail.com");
        Client c02 = new Client("pseudo-02", "12345678", "email.cli02@free.fr");
        
        em.persist(c01);
        em.persist(c02);
        em.flush();
        
        Produit p01 = new Produit("REF-AA-0001", "produit-01", "super 01 produit", 6.55f, 5);
        Produit p02 = new Produit("REF-AB-0001", "produit-02", "top 01 produit", 10.20f, 7);
        Produit p03 = new Produit("REF-AA-0022", "produit-03", "super 02 produit", 7.45f, 6);
        Produit p04 = new Produit("REF-AB-0111", "produit-04", "top 02 produit", 12.20f, 10);
        Produit p05 = new Produit("REF-AA-0201", "produit-05", "super 03 produit", 16.55f, 4);
        Produit p06 = new Produit("REF-AB-5001", "produit-06", "top 03 produit", 14.20f, 5);
        
        em.persist(p01);
        em.persist(p02);
        em.persist(p03);
        em.persist(p04);
        em.persist(p05);
        em.persist(p06);
        
    }

    
}
