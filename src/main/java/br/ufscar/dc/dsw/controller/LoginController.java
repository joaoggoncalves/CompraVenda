package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal, @Valid @ModelAttribute RedirectModel model, BindingResult result) {
        if (!result.hasErrors() && principal != null) {
            return "redirect:" + model.getContinue();
        }
        return "login";
    }
    
    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        
        String role =  authResult.getAuthorities().toString();
        
        if (role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "admin/adminindex"));                            
        } else if (role.contains("ROLE_USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "user/userindex"));
        } else if (role.contains("ROLE_LOJA")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "loja/lojaindex"));
        }
    }

    
}
