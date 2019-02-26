/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.secondaires;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionsBeans.CreationDonneesLocal;

/**
 *
 * @author cdi104
 */
public class CreerDonnees
        implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        CreationDonneesLocal creationDonnees = lookupCreationDonneesLocal();

        String url = "/WEB-INF/home.jsp";

        try {
            creationDonnees.creerDonnes();
            request.setAttribute("classe", "alert alert-success");
            request.setAttribute("msg", "creation des données effectuée");

        } catch (Exception ex) {
            request.setAttribute("classe", "alert alert-danger");
            request.setAttribute("msg", ex.getMessage());
        }
        url = "/WEB-INF/home.jsp";
        return url;
    }

    private CreationDonneesLocal lookupCreationDonneesLocal() {
        try {
            Context c = new InitialContext();
            return (CreationDonneesLocal) c.lookup("java:global/magasinEJB/magasinEJB-ejb/CreationDonnees!sessionsBeans.CreationDonneesLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
