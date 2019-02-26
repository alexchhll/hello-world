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
            String origine1 = request.getParameter("origine1");
            String origine = request.getParameter("origine");
            if("catalogue".equals(origine)){
                System.out.println("--------->"+origine);
                url = "ControleurMain?section=catalogue";
            }
            if("detail".equals(origine)){
                System.out.println("------>>>>><"+origine);
                
                url = "ControleurMain?section=detail-produit&ref="+ref;
                System.out.println("ooooooooooooo"+ref);
            }
            if("panier".equals(origine)){
                url = "ControleurMain?section=detail-panier";
            }
            if("ajax".equals(origine)){
                 url = "ControleurMain?section=menu-main";
            }
            
            request.setAttribute("redirect", true);
        /*
            -------
                il y a une perte d'infor sur la page qui demandé le detail du produit
                c'est à vous de vous amusez....
            faire 2 variable origine donc ajouter origine 1 et mettre origine a la suite de origine 1 sur la jsp
            -------
        */
        return url;
    }
}
