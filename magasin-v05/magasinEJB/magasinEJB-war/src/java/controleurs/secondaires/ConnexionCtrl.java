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

public class ConnexionCtrl
        implements Serializable, SousControleurInterface {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        GestionClientLocal gestionClient = lookupGestionClientLocal();
        String url = "/WEB-INF/home.jsp";
        String email = request.getParameter("email");
        String mdp = request.getParameter("pswd");
        try {
            Client user = gestionClient.seConnecter(email, mdp);
            session.setAttribute("user", user);
            request.setAttribute("classe", "alert alert-success");
            request.setAttribute("msg", "Bonjour " + user.getPseudo() + " !!");
            url = "/WEB-INF/home.jsp";
        } catch (Exception ex) {
            request.setAttribute("emailFourni", email);
            request.setAttribute("errLogin", ex.getMessage());
            url = "/WEB-INF/login-form.jsp";
        }
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
