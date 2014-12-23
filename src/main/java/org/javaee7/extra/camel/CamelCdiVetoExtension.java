package org.javaee7.extra.camel;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 *
 * @author Markus Eisele
 */
public class CamelCdiVetoExtension implements Extension {
    void interceptProcessAnnotatedTypes(@Observes ProcessAnnotatedType processAnnotatedType)
    {
        if (processAnnotatedType.getAnnotatedType().getJavaClass().getName().equals("org.apache.camel.cdi.CdiCamelContext")) {
            processAnnotatedType.veto();
        }
    }
}
