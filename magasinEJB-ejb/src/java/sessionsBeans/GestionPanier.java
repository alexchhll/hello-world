package sessionsBeans;

import entites.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import obj.LignePanier;

@Stateful
public class GestionPanier implements GestionPanierLocal {    
    
    @EJB
    private GestionCatalogueLocal gestionCatalogue;
    
    private HashMap<String, LignePanier> panier;
    
    @PostConstruct
    public void init(){
        panier = new HashMap<>();
    }
    
    @Override
    public void ajouter(String ref){
        if(ref != null){
            ref = ref.trim();
        }else{
            return ;
        }
        if(panier.containsKey(ref)){
            LignePanier lp = panier.get(ref);
            int newQte = lp.getQte()+1;
            if(newQte > lp.getArticle().getStock()){
                newQte = lp.getArticle().getStock();
            }
            lp.setQte(newQte);            
        }else{
            Produit p = gestionCatalogue.selectProduitbyRef(ref);
            if(p!=null && p.getStock()>0){
                LignePanier newLp= new LignePanier(p);
                panier.put(ref, newLp);
            }
        }
    }
    
    @Override
    public Collection<LignePanier> getMesLignesPaniers(){
        return panier.values();
    }
    
    @Override
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
    @Override
    public float getTotalHT(){
        Collection<LignePanier> lignes = panier.values();
        float somme = 0;
        for(LignePanier l : lignes){
            somme += l.getPrixHT();
        }
        return somme;
    }
    
    @Override
    public int getNbArticles(){
        Collection<LignePanier> lignes = panier.values();
        int nombre = 0;
        for(LignePanier l : lignes){
            nombre += l.getQte();
        }
        return nombre;
    }
    
}
