package zb.Team.showticket.provider.filter;

import lombok.RequiredArgsConstructor;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.ProviderVo;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.provider.service.ProviderService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/provider/+")
@RequiredArgsConstructor
public class ProviderFilter implements Filter {
    private final JwtAuthProvider provider;
    private final ProviderService providerService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("AUTH-TOKEN");
        if (!provider.validationToken(token)) {
            throw new ServletException("유효하지 않은 접근입니다.");
        }
        ProviderVo vo = provider.getProviderVo(token);
        providerService.findByIdAndEmail(vo.getId(),
                vo.getEmail()).orElseThrow(() -> new ServletException("유효하지 않은 접근입니다."));
        chain.doFilter(request,response);
    }
}
