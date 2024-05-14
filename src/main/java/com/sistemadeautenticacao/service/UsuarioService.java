package com.sistemadeautenticacao.service;

import com.sistemadeautenticacao.exception.UsuarioNotFoundException;
import com.sistemadeautenticacao.model.Usuario;
import com.sistemadeautenticacao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com ID: " + id));
    }
  
    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com nome: " + nome));
    }
    

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    
   
    public Usuario update(Long id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNome(usuario.getNome());
                    existingUser.setEmail(usuario.getEmail());
                    existingUser.setSenha(usuario.getSenha());
                    return usuarioRepository.save(existingUser);
                }).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com ID: " + id));

}
}