package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.security.UsuarioSS;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalhadoServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException(Mensagens.MSG_OBJETO_NAO_ENCONTRADO.replace("{}",email));
        }
        return new UsuarioSS(cliente.getId(),cliente.getSenha(),cliente.getEmail(),cliente.getPerfis());
    }
}
