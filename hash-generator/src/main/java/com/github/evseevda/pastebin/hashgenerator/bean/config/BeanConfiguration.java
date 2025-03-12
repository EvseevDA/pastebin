package com.github.evseevda.pastebin.hashgenerator.bean.config;

import com.github.evseevda.pastebin.hashgenerator.util.lock.LockUtils;
import com.github.evseevda.pastebin.hashgenerator.util.lock.LockUtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    @Scope("prototype")
    public LockUtils prototypeLockUtils() {
        return new LockUtilsImpl();
    }

}
