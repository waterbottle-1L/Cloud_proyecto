package com.SistemaFacturacion.facturacion.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Arrays;

@Component
public class JwtBeansInspector implements ApplicationRunner {
    @Autowired
    private ApplicationContext ctx;

    @Override
    public void run(ApplicationArguments args) {
        String[] names = ctx.getBeanNamesForType(JwtEncoder.class);
        System.out.println("JwtEncoder beans found: " + Arrays.toString(names));
        for (String name : names) {
            Object bean = ctx.getBean(name);
            System.out.println(" - " + name + " -> " + bean.getClass().getName());
        }
    }
}
