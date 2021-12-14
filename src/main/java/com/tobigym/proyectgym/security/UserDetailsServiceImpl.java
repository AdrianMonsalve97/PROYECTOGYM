package com.tobigym.proyectgym.security;

// import com.tobigym.proyectgym.models.Role;
import com.tobigym.proyectgym.models.Usuariojwt;
import com.tobigym.proyectgym.repository.UsuarioRepository;

// import java.util.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// import static java.util.Collection.emptyList;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuariojwt usuarioOpt = usuarioRepository.findByUsuario(username);
        if (usuarioOpt == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> roles = getRoles(usuarioOpt);
        return new org.springframework.security.core.userdetails.User(usuarioOpt.getUsuario(),
                usuarioOpt.getPassword(),
                roles);
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Optional<Usuariojwt> usuarioOpt = usuarioRepository.findByUsuario(username);
    // User userDetails = null;

    // if (usuarioOpt.isPresent()) {
    // Usuariojwt usuario = usuarioOpt.get();
    // userDetails = new
    // org.springframework.security.core.userdetails.User(usuario.getUsuario(),
    // usuario.getPassword(), emptyList());
    // } else {
    // throw new UsernameNotFoundException("El usuario no existe" + username);
    // }
    // return userDetails;
    // }

    // private Collection<? extends GrantedAuthority> emptyList() {
    // return Collections.emptyList();
    // }

    private List<SimpleGrantedAuthority> getRoles(Usuariojwt usuario) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getName()));

        return roles;
    }
}
