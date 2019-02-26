
package sessionsBeans;

import entites.Client;
import javax.ejb.Local;

@Local
public interface GestionClientLocal {

    public Client seConnecter(String email, String mdp) throws Exception;

    public Client modifier(Client user);
    
}
