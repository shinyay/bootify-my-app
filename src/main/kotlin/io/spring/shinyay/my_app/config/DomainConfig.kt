package io.spring.shinyay.my_app.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EntityScan("io.spring.shinyay.my_app.domain")
@EnableJpaRepositories("io.spring.shinyay.my_app.repos")
@EnableTransactionManagement
class DomainConfig
