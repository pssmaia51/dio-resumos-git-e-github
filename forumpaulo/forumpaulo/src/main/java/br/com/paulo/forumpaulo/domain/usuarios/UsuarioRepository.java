package br.com.paulo.forumpaulo.domain.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    Page<Usuarios> findAllByAtivoTrue(Pageable paginacao);

    UserDetails findByLogin(String login);
}
