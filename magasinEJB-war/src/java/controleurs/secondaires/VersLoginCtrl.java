
package controleurs.secondaires;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class VersLoginCtrl implements Serializable, SousControleurInterface{

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/login-form.jsp";
    }
    
}
