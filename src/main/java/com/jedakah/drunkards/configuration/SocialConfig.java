package com.jedakah.drunkards.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.social.vkontakte.security.VKontakteAuthenticationService;

@Configuration
public class SocialConfig {

  public static final String CLIENT_ID = "6264994";
  public static final String CLIENT_SECRET = "tZLkbEVHYjOdqf1eldDI";

  @Bean
  public ConnectionFactoryLocator connectionFactoryLocator(Environment environment) {
    ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

    registry.addConnectionFactory(vkConnectionFactory(environment));

    return registry;
  }

  @Bean
  public ConnectionFactory<VKontakte> vkConnectionFactory(Environment environment) {
    VKontakteConnectionFactory factory = new VKontakteConnectionFactory(
        CLIENT_ID, CLIENT_SECRET);
    factory.setScope("email");
    return factory;
  }

//  @Bean
//  @ConfigurationProperties(prefix = "social.endpoint")
//  public Map<String, String> socialEndpoints() {
//    return new HashMap<>();
//  }


//  private VKontakteSocialInfoConverter vKontakteInfoConverter(Environment environment) {
//    return new VKontakteSocialInfoConverter();
//  }

//  @Bean
//  public SocialInfoLocator socialInfoLocator(Environment environment) {
//    SocialInfoLocator socialInfoLocator = new SocialInfoLocatorImpl();
//    socialInfoLocator.addInfoConverter(vKontakteInfoConverter(environment));
//    socialInfoLocator.addInfoConverter(googleInfoConverter(environment));
//
//    return socialInfoLocator;
//  }
}