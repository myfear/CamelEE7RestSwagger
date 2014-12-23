/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.camel.component.swagger;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.cdi.CdiCamelContext;

/**
 *
 * @author myfear
 */
public class CdiRestSwaggerApiDeclarationServlet extends RestSwaggerApiDeclarationServlet {

    @Inject
    CdiCamelContext context;

    @Override
    public CamelContext lookupCamelContext(ServletConfig config) {
        return context;
    }
}
