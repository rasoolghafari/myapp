package ir.rasoolghafari.myapp.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Log4j2
public class AclAuthorityFilter implements Filter {

    @Value("${myapp.acl.enabled:true}")
    private boolean aclEnabled;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("AclAuthorityFilter initialized, acl enabled: {}", aclEnabled);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!aclEnabled) {
            log.info("ACL disabled and skipped!");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("authentication is {}", authentication);
            Object principal = authentication.getPrincipal();
            log.info("principal is {}", principal);

            if (principal != null && !principal.equals("anonymousUser")) {
                authentication.getAuthorities().forEach(e -> log.info(e.getAuthority()));
            }
            else {
                log.error("Principal is null");
            }
        } catch (Exception ex) {
            log.error("exception occured!");
            log.error(ex.getMessage());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
