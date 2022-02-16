package br.com.aurock.crusobackend.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;
    private static final String HEADER = "Authorization";
    private static final String TIPO_HEADER = "Bearer ";


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService){
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String reader = request.getHeader(HEADER);
        if(reader != null && reader.startsWith(TIPO_HEADER)){
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(reader.substring(7));
            if (authenticationToken != null){
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication( String token) {
        if(jwtUtil.isTokenValido(token)){
            String userName = jwtUtil.getUserName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        }
        return null;
    }
}
