package controleurs;

import controleurs.secondaires.CatalogueCtrl;
import controleurs.secondaires.ConnexionCtrl;
import controleurs.secondaires.CreerDonnees;
import controleurs.secondaires.DetailPanierCtrl;
import controleurs.secondaires.DetailProduitCtrl;
import controleurs.secondaires.MenuMainCtrl;
import controleurs.secondaires.ModificationPseudoCtrl;
import controleurs.secondaires.OperationsPanierCtrl;
import controleurs.secondaires.SousControleurInterface;
import controleurs.secondaires.VersLoginFormCtrl;
import entites.Client;
import entites.Produit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import obj.LignePanier;
import sessionsBeans.CreationDonneesLocal;
import sessionsBeans.GestionCatalogueLocal;
import sessionsBeans.GestionClientLocal;
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
//        mp.put("vers-login-form", new VersLoginFormCtrl());
//        mp.put("detail-produit", new DetailProduitCtrl());
//        mp.put("menu-main", new MenuMainCtrl());
//        mp.put("creer-donnees", new CreerDonnees());
        
        
    }
    
    @EJB
    private GestionClientLocal gestionClient;
    
    @EJB
    private GestionCatalogueLocal gestionCatalogue;
    
    @EJB
    private CreationDonneesLocal creationDonnees;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        if(session.getAttribute("gestionPanier")== null){
            session.setAttribute("gestionPanier", lookupGestionPanierLocal());
        }
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        
        String url = "/WEB-INF/home.jsp";
        String section = request.getParameter("section");
        
        if(mp.containsKey(section)){
            SousControleurInterface ctrl = mp.get(section) ;
            url = ctrl.process(request, response);
        }
       
        
//        if("catalogue".equals(section)){
//            CatalogueCtrl caCtrl = new CatalogueCtrl();
//            
////            List<Produit> produits = gestionCatalogue.selectAllProduits();
////            request.setAttribute("produits", produits);
////            url = "/WEB-INF/catalogue.jsp";
//        }
        
//        if("vers-login-form".equals(section)){
//            url = "/WEB-INF/login-form.jsp";
//        }
        
//        if("detail-produit".equals(section)){
//            String ref = request.getParameter("ref");
//            Produit prod = gestionCatalogue.selectProduitbyRef(ref);
//            request.setAttribute("produit", prod);
//            String origine = request.getParameter("origine");
//            if("catalogue".equals(origine)){
//                request.setAttribute("urlRetour", "ControleurMain?section=catalogue");
//            }
//            if("panier".equals(origine)){
//                request.setAttribute("urlRetour", "ControleurMain?section=detail-panier");
//            }
//            url = "/WEB-INF/detail-produit.jsp";
//        } //erreur de MVC 1 vers MVC 2 ici ???
        
//        if("creer-donnees".equals(section)){
//            try{
//            creationDonnees.creerDonnes();
//            request.setAttribute("classe", "alert alert-success");
//            request.setAttribute("msg", "creation des données effectuée");
//            
//            }catch(Exception ex){
//                request.setAttribute("classe", "alert alert-danger");
//                request.setAttribute("msg", ex.getMessage());
//            }
//            url = "/WEB-INF/home.jsp";
//        }
        
//        if("menu-main".equals(section)){
//            int qte = gestionPanier.getNbArticles();
//            float montantHt = gestionPanier.getTotalHT();
//            String info = qte + " article";
//            if(qte > 1){
//                info +="s";
//            }
//            info += " ( "+montantHt+" € HT )";
//            request.setAttribute("infoPanier", info);
//            Client cli = (Client) session.getAttribute("user");
//            if(cli !=null){
//                url = "/WEB-INF/menus/menu-main-logged.jsp";
//            }else{
//                url = "/WEB-INF/menus/menu-main.jsp";
//            }            
//        }
        
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
