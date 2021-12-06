package ir.rasoolghafari.myapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${myapp.acl.enabled:true}")
    private boolean aclEnabled;

    @Override
    public void configure(WebSecurity web) throws Exception {
        if (!aclEnabled) {
            web.ignoring().antMatchers("/**");
        }
    }

    /*@ConditionalOnProperty(prefix = "myapp.acl", name = "enabled", havingValue = "true")
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    static class Acl {
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

}
