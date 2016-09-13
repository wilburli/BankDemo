package com.bankdemo.configurations;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ws.config.annotation.EnableWs;

/**
 * Created by Ilyas.Kuanyshbekov on 08.09.2016.
 */
@Configuration
@EnableWs
@ImportResource(value = {"classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml", "classpath:META-INF/cxf/cxf-extension-jaxws.xml"})
public class CxfConfig {

    @Autowired
    private SpringBus bus;

    @Bean
    public ServletRegistrationBean cxfServletRegistrationBean() {
        CXFServlet cxfServlet = new CXFServlet();
        cxfServlet.setBus(bus);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(cxfServlet, "/api/*");//http://localhost:8080/api/Service?wsdl
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setName("CXFServlet");
        return servletRegistrationBean;
    }

}
