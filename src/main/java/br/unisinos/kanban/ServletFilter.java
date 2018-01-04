package br.unisinos.kanban;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Alex Carvalho
 */
@WebFilter(filterName = "FilterLogin", urlPatterns = {"/pages/*"})
public class ServletFilter implements Filter {

    @Inject
    private Instance<SessionContext> sessionContext;

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (sessionContext.get().getActiveUser() != null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.xhtml").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
