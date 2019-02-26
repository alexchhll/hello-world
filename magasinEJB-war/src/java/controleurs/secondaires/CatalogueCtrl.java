package controleurs.secondaires;

import entites.Produit;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsBeans.GestionCatalogueLocal;

public class CatalogueCtrl implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        GestionCatalogueLocal gestionCatalogue = lookupGestionCatalogueLocal();
        List<Produit> produits = gestionCatalogue.selectAllProduits();
        request.setAttribute("produits", produits);
        return "/WEB-INF/catalogue.jsp";
    }

    private GestionCatalogueLocal lookupGestionCatalogueLocal() {
        try {
            Context c = new InitialContext();
            return (GestionCatalogueLocal) c.lookup("java:global/magasinEJB/magasinEJB-ejb/GestionCatalogue!sessionsBeans.GestionCatalogueLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
