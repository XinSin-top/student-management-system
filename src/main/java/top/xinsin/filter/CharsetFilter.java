package top.xinsin.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter  {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("uft-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter关闭");
    }
}
