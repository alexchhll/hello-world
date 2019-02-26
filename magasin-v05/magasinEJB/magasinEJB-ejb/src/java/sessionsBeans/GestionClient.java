package sessionsBeans;

import entites.Client;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class GestionClient implements GestionClientLocal {
    @PersistenceContext(unitName = "magasinEJB-ejbPU")
    private EntityManager em;
    

    /*TODO*/
    // l'inscription
    
    
    /*TODO*/
    // modification des infos du compte
    
    
    @Override
    public Client seConnecter(String email, String mdp)throws Exception{
        if(email != null){
            email = email.trim();
        }else{
            email="";
        }
        TypedQuery<Client> qr = em.createNamedQuery("entites.Client.login", Client.class);
        qr.setParameter("paramEmail", email);
        qr.setParameter("paramMdp", mdp);
        try{
            Client user = qr.getSingleResult();
            return user;
        }catch(NoResultException ex){
            throw new Exception("Email ou mot de passe invalide");
        }
    }
    
    @Override
    public Client modifier(Client user){
        user = em.merge(user);
        String newPseudo = "new-"+user.getPseudo();
        user.setPseudo(newPseudo);
        return user;
    }

    
}
