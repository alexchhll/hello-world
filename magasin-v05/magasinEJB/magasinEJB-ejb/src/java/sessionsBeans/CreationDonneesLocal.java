package sessionsBeans;

import javax.ejb.Local;


@Local
public interface CreationDonneesLocal {

    public void creerDonnes()throws Exception;
    
}
