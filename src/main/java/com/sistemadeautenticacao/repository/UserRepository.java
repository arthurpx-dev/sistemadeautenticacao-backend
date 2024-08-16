package com.sistemadeautenticacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadeautenticacao.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
    // Adicione outros métodos de busca conforme necessário

    Optional<User> findByEmail(String email);
}