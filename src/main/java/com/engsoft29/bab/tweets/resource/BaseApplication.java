package com.engsoft29.bab.tweets.resource;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("api")
public class BaseApplication extends Application {
	
	public BaseApplication() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setContact("consultoria@bab.com");
        beanConfig.setTitle("Serviço de processamento de hashtag");
        beanConfig.setDescription("Projeto RESTFul para processamento de tweets, baseado em hashtags. Pilha de tecnologia: .Net, Java (JavaEE 7 - JAX-RS, EJB) e Apache Spark.");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("www.babconsultoria.com");
        beanConfig.setBasePath("/tweets/api");
        beanConfig.setResourcePackage("com.engsoft29.bab.tweets.resource");
        beanConfig.setScan(true);
	}
}