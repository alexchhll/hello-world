package obj;

import entites.Produit;
import java.io.Serializable;

public class LignePanier implements Serializable{
    private Produit article;
    private int qte;

    public LignePanier() {
    }

    public LignePanier(Produit article) {
        this.article = article;
        qte = 1;
    }
    
    

    public Produit getArticle() {
        return article;
    }

    public void setArticle(Produit article) {
        this.article = article;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
    public float getPrixHT(){
        return qte * article.getPrix();
    }

    @Override
    public String toString() {
        return article.getNom()+"(" +article.getRef() +", qte=" + qte + ')';
    }
    
    
    
}
