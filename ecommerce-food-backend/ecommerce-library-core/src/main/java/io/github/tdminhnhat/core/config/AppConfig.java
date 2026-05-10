package io.github.tdminhnhat.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"io.github.tdminhnhat"})
@EnableAspectJAutoProxy
public class AppConfig {
}
