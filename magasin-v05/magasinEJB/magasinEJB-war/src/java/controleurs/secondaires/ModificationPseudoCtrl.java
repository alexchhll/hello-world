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
import sessionsBeans.GestionClientLocal;

public class ModificationPseudoCtrl implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        GestionClientLocal gestionClient = lookupGestionClientLocal();
        String url = "/WEB-INF/home.jsp";
        HttpSession session = request.getSession();
        Client c = (Client) session.getAttribute("user");
        c = gestionClient.modifier(c);
        session.setAttribute("user", c);
        request.setAttribute("classe", "alert alert-success");
        request.setAttribute("msg", "modification : " + c.getPseudo() + " !!");
        url = "/WEB-INF/home.jsp";
        return url;
    }

    private GestionClientLocal lookupGestionClientLocal() {
        try {
            Context c = new InitialContext();
            return (GestionClientLocal) c.lookup("java:global/magasinEJB/magasinEJB-ejb/GestionClient!sessionsBeans.GestionClientLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
