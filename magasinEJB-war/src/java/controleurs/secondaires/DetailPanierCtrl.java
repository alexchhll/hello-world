package controleurs.secondaires;

import java.io.Serializable;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.LignePanier;
import sessionsBeans.GestionPanierLocal;

public class DetailPanierCtrl implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        Collection<LignePanier> lignes = gestionPanier.getMesLignesPaniers();
        float montant = gestionPanier.getTotalHT();
        request.setAttribute("montant", montant);
        request.setAttribute("panier", lignes);        
        return "/WEB-INF/detail-panier.jsp";
    }
}
