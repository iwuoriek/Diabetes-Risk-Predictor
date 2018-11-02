/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.config;

import com.sun.faces.config.FacesInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 *
 * @author Kelechi
 */
public class WebAppInitilizer extends FacesInitializer implements WebApplicationInitializer{
    

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(DatabaseConfig.class, ComponentConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
    }
}
