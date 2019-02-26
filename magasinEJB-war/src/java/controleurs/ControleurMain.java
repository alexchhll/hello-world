package controleurs;

import controleurs.secondaires.SousControleurInterface;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionsBeans.GestionPanierLocal;

public class ControleurMain extends HttpServlet {
    
    private HashMap<String, SousControleurInterface> mp;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        
        mp = new HashMap<>();
//        mp.put("modifier-force", new ModificationPseudoCtrl());
//        mp.put("detail-panier", new DetailPanierCtrl());
//        mp.put("operations-panier", new OperationsPanierCtrl());
//        mp.put("se-connecter", new ConnexionCtrl());
//        mp.put("catalogue", new CatalogueCtrl());
//        mp.put("vers-login-form", new VersLoginCtrl());
//        mp.put("detail-produit", new DetailProduitCtrl());
//        mp.put("creer-donnees", new CreationDonneesCtrl());
//        mp.put("menu-main", new MenuMainCtrl());
        Enumeration<String> clefs = config.getInitParameterNames();
        while(clefs.hasMoreElements()){
            String cle = clefs.nextElement();
            String valeur = config.getInitParameter(cle);
            try {
                SousControleurInterface ctrl = (SousControleurInterface) Class.forName(valeur).newInstance();
                mp.put(cle, ctrl);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleurMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ControleurMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ControleurMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if(session.getAttribute("gestionPanier")== null){
            session.setAttribute("gestionPanier", lookupGestionPanierLocal());
        }
        
        String url = "/WEB-INF/home.jsp";
        String section = request.getParameter("section");
        
        if(mp.containsKey(section)){
            SousControleurInterface ctrl = mp.get(section) ;
            url =ctrl.process(request, response);
        }
        
        url = response.encodeURL(url);
        Boolean redirect = (Boolean) request.getAttribute("redirect");
        if(redirect != null && redirect){
            response.sendRedirect(url);
        }else{
            getServletContext().getRequestDispatcher(url).include(request, response);
        }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private GestionPanierLocal lookupGestionPanierLocal() {
        try {
            Context c = new InitialContext();
            return (GestionPanierLocal) c.lookup("java:global/magasinEJB/magasinEJB-ejb/GestionPanier!sessionsBeans.GestionPanierLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
