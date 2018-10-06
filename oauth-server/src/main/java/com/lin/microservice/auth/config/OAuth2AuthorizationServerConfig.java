package com.lin.microservice.auth.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${app.oauth.token-store:jdbc}")
    private String tokenStoreType;
	
    @Value("${app.oauth.jwt.key-type:secret}")
    private String keyType;
	
    @Value("${app.oauth.jwt.sign-key:123}")
    private String signKey;
	
    @Value("${app.oauth.jwt.key-store.file:mytest.jks}")
    private String keyStoreFile;
	
    @Value("${app.oauth.jwt.key-store.password:mypass}")
    private String keyStorePassword;
	
    @Value("${app.oauth.jwt.key-store.key:mytest}")
    private String keyName;
	
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    	if (tokenStoreType.equals("jwt")) {
            tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
    	} else  {
            tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
    	}
        endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore() {
    	if (tokenStoreType.equals("jwt")) {
            return new JwtTokenStore(accessTokenConverter());
    	} else  {
            return new JdbcTokenStore(dataSource);
    	}
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        if (keyType.equals("secret")) {
	        converter.setSigningKey(signKey);
        } else {
	        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keyStoreFile), keyStorePassword.toCharArray());
	        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyName));
        }
        return converter;
    }
}
