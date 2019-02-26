package controleurs.secondaires;

import entites.Produit;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsBeans.GestionCatalogueLocal;

public class DetailProduitCtrl implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        GestionCatalogueLocal gestionCatalogue = lookupGestionCatalogueLocal();
        String ref = request.getParameter("ref");
        Produit prod = gestionCatalogue.selectProduitbyRef(ref);
        request.setAttribute("produit", prod);
        String origine = request.getParameter("origine");
        if ("catalogue".equals(origine)) {
            request.setAttribute("urlRetour", "ControleurMain?section=catalogue");
        }
        if ("panier".equals(origine)) {
            request.setAttribute("urlRetour", "ControleurMain?section=detail-panier");
        }
        return "/WEB-INF/detail-produit.jsp";
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
