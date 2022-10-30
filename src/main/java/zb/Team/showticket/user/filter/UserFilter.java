package zb.Team.showticket.user.filter;

import lombok.RequiredArgsConstructor;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.user.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/+")
@RequiredArgsConstructor
public class UserFilter implements Filter {

    private final JwtAuthProvider provider;
    private final UserService userService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("AUTH-TOKEN");
        if (!provider.validationToken(token)) {
            throw new ServletException("유효하지 않은 접근입니다.");
        }
        UserVo vo = provider.getUserVo(token);
        userService.findByIdAndEmail(vo.getId(),
                vo.getEmail()).orElseThrow(() -> new ServletException("유효하지 않은 접근입니다."));
        chain.doFilter(request,response);
    }
}
