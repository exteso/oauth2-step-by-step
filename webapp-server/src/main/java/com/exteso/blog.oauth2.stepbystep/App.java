package com.exteso.com.exteso.blog.oauth2.stepbystep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class App {

    @Autowired
    private OAuth2RestTemplate resourceServerProxy;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.GET)
    public Map<String, String> getMessage() {
        return resourceServerProxy.getForObject("http://localhost:9090", Map.class);
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.POST)
    public void saveMessage(@RequestBody String newMessage) {
        resourceServerProxy.postForLocation("http://localhost:9090", newMessage);
    }

    @Configuration
    public static class OauthClientConfiguration {

        @Bean
        @ConfigurationProperties("resourceServerClient")
        public ClientCredentialsResourceDetails getClientCredentialsResourceDetails() {
            return new ClientCredentialsResourceDetails();
        }

        @Bean
        public OAuth2RestTemplate restTemplate() {
            AccessTokenRequest atr = new DefaultAccessTokenRequest();
            return new OAuth2RestTemplate(getClientCredentialsResourceDetails(), new DefaultOAuth2ClientContext(atr));
        }


    }
}
