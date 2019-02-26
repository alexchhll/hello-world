package controleurs.secondaires;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionsBeans.GestionPanierLocal;


public class OperationsPanierCtrl implements Serializable, SousControleurInterface {
    
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response){
        String url = "/WEB-INF/home.jsp";
        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        String ref = request.getParameter("ref");
            String operation = request.getParameter("operation");
            if("ajouter".equals(operation)){
                gestionPanier.ajouter(ref);
            }
//            if("vider".equals(operation)){
//                
//            }
//            if("supprimerLigne".equals(operation)){
//                
//            }
            
            String origine = request.getParameter("origine");
            if("catalogue".equals(origine)){
                url = "ControleurMain?section=catalogue";
            }
            if("detail".equals(origine)){
                url = "ControleurMain?section=detail-produit&ref="+ref;
            }
            if("panier".equals(origine)){
                url = "ControleurMain?section=detail-panier";
            }
            
            request.setAttribute("redirect", true);
        
        return url;
    }
}
