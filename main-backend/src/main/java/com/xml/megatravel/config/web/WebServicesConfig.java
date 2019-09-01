package com.xml.megatravel.config.web;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServicesConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "agentWsdl")
    public DefaultWsdl11Definition agentWsdl11Definition(XsdSchema agentSchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("agentPort");
        wsdl11Definition.setLocationUri("/ws/agent");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/agent");
        wsdl11Definition.setSchema(agentSchema);
        return wsdl11Definition;
    }

    @Bean(name = "propertyWsdl")
    public DefaultWsdl11Definition propertyWsdl11Definition(XsdSchema propertySchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("propertyPort");
        wsdl11Definition.setLocationUri("/ws/property");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/property");
        wsdl11Definition.setSchema(propertySchema);
        return wsdl11Definition;
    }

    @Bean(name = "reservationWsdl")
    public DefaultWsdl11Definition reservationWsdl11Definition(XsdSchema reservationSchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("reservationPort");
        wsdl11Definition.setLocationUri("/ws/reservation");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/reservation");
        wsdl11Definition.setSchema(reservationSchema);
        return wsdl11Definition;
    }

    @Bean(name = "messageWsdl")
    public DefaultWsdl11Definition messageWsdl11Definition(XsdSchema messageSchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("messagePort");
        wsdl11Definition.setLocationUri("/ws/message");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/message");
        wsdl11Definition.setSchema(messageSchema);
        return wsdl11Definition;
    }

    @Bean(name = "serviceWsdl")
    public DefaultWsdl11Definition serviceWsdl11Definition(XsdSchema serviceSchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("servicePort");
        wsdl11Definition.setLocationUri("/ws/service");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/service");
        wsdl11Definition.setSchema(serviceSchema);
        return wsdl11Definition;
    }

    @Bean(name = "typeWsdl")
    public DefaultWsdl11Definition typeWsdl11Definition(XsdSchema typeSchema)
    {
        final DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("typePort");
        wsdl11Definition.setLocationUri("/ws/type");
        wsdl11Definition.setTargetNamespace("http://www.xml.com/megatravel/soap/model/type");
        wsdl11Definition.setSchema(typeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema agentSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/agent.xsd"));
    }

    @Bean
    public XsdSchema propertySchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/property.xsd"));
    }

    @Bean
    public XsdSchema reservationSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/reservation.xsd"));
    }

    @Bean
    public XsdSchema messageSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/message.xsd"));
    }

    @Bean
    public XsdSchema serviceSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/service.xsd"));
    }

    @Bean
    public XsdSchema typeSchema()
    {
        return new SimpleXsdSchema(new ClassPathResource("xml/type.xsd"));
    }

}
