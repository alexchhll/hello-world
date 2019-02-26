/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.secondaires;

import entites.Client;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionsBeans.GestionPanierLocal;

/**
 *
 * @author cdi104
 */
public class MenuMainCtrl
        implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        String url;

        int qte = gestionPanier.getNbArticles();
        float montantHt = gestionPanier.getTotalHT();
        String info = qte + " article";
        if (qte > 1) {
            info += "s";
        }
        info += " ( " + montantHt + " € HT )";
        request.setAttribute("infoPanier", info);
        Client cli = (Client) session.getAttribute("user");
        if (cli != null) {
            return url = "/WEB-INF/menus/menu-main-logged.jsp";
        } else {
            return url = "/WEB-INF/menus/menu-main.jsp";
        }
    }

}
