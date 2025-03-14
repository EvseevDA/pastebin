package com.github.evseevda.pastebin.hashgenerator.config;

import com.redis.testcontainers.RedisContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class IntegrationTestConfig {

    public static final RedisContainer REDIS_CONTAINER;

    static {
        REDIS_CONTAINER = new RedisContainer(DockerImageName.parse("redis:latest"));
        REDIS_CONTAINER.start();
    }

    @Bean
    @Primary
    public RedisConnectionFactory testJedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(REDIS_CONTAINER.getHost());
        configuration.setPort(REDIS_CONTAINER.getFirstMappedPort());
        return new JedisConnectionFactory(configuration);
    }

}
