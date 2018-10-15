package com.lin.microservice.cloud.edge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Value("${app.redis.host:localhost}")
    private String redisHost;
	
    @Value("${app.redis.port:6379}")
    private String redisPort;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(Integer.parseInt(redisPort));
        redisStandaloneConfiguration.setDatabase(0);
        //redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));

        JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        //jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());

        return jedisConFactory;
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
}
