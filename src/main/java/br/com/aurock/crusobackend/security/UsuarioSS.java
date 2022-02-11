package br.com.aurock.crusobackend.security;

import br.com.aurock.crusobackend.domain.enuns.Perfil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
public class UsuarioSS implements UserDetails {

    private static final long serialVersionUID = 1l;

    private Integer id;
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioSS(Integer id, String password, String username, Set<Perfil> perfis){
        this.id = id;
        this.password = password;
        this.username = username;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
