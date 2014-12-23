package org.javaee7.extra.camel;

import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.CdiCamelContext;
import org.javaee7.extra.camel.rest.UserRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Arun Gupta / Markus Eisele
 */
@Singleton
@Startup
public class Bootstrap {
    
    @Inject
    CdiCamelContext context;
    
    Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    
    @PostConstruct
    public void init() {
        logger.info(">> Create CamelContext and register Camel Route.");
        
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    
                    from("timer://timer1?period=1000")
                            .setBody()
                            .simple("Camel")
                            .beanRef("helloCamel", "sayHello")
                            .log(">> Response : ${body}");
                    
                }
            });
            context.addRoutes(new UserRouteBuilder());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Bootstrap.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Start Camel Context
        context.start();
        
        logger.info(">> CamelContext created and camel route started.");
    }
    
    @PreDestroy
    public void shutdown() {
        // Graceful Shutdown Camel Context
        context.stop();
        logger.info(">> CamelContext stopped .");
    }
    
}
