package com.bankdemo.configurations;

import com.bankdemo.util.CxfAutoRegistrationEndpointBean;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContextException;

import javax.jws.WebService;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Ilyas.Kuanyshbekov on 13.09.2016.
 * Adding cxf endpoint beans programmatically
 */
@Component
public class CxfEnpointsRegistrationConfig implements BeanDefinitionRegistryPostProcessor {

    private Map<String, String> cxfEndpoints = new HashMap<>();

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        try {
            // getting all beans
            List<String> beanDefinitionNames = new ArrayList(Arrays.asList(beanDefinitionRegistry.getBeanDefinitionNames()));
            for (String beanDefinitionName : beanDefinitionNames) {

                BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanDefinitionName);
                if (beanDefinition.getBeanClassName() == null) {
                    continue;
                }

                Class<?> c = getClass().getClassLoader().loadClass(beanDefinition.getBeanClassName());
                for (Class<?> i : c.getInterfaces()) {

                    if (! i.isAnnotationPresent(CxfAutoRegistrationEndpointBean.class)) {
                        continue;
                    }
                    if (! i.isAnnotationPresent(WebService.class)) {
                        continue;
                    }


                    Field f = i.getField("WS_SERVICE_NAME");
                    String service_name = (String) f.get(c);

                    if (service_name != null) {
                        cxfEndpoints.put("/"+ service_name, beanDefinitionName);
                    } else {
                        logger.error("Failed to register endpoint bean " + beanDefinitionName);
                    }
                }
            }
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            logger.error(e);
            throw new ApplicationContextException("Failed to generate soap endpoint beans definition " + e);
        }


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    public Map<String, String> getCxfEndpoints() {
        return cxfEndpoints;
    }

    public void setCxfEndpoints(Map<String, String> cxfEndpoints) {
        this.cxfEndpoints = cxfEndpoints;
    }
}
