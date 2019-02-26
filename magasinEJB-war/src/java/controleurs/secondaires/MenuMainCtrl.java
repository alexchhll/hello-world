package controleurs.secondaires;

import entites.Client;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionsBeans.GestionPanierLocal;

public class MenuMainCtrl implements Serializable, SousControleurInterface{

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String url = "/WEB-INF/home.jsp";
        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        int qte = gestionPanier.getNbArticles();
            float montantHt = gestionPanier.getTotalHT();
            String info = qte +" article";
            if(qte > 1){
                info +="s";
            }
            info += " ( "+montantHt+" € HT )";
            request.setAttribute("infoPanier", info);
            Client cli = (Client) session.getAttribute("user");
            if(cli !=null){
                url = "/WEB-INF/menus/menu-main-logged.jsp";
            }else{
                url = "/WEB-INF/menus/menu-main.jsp";
            }
            return url;
    }
    
}
