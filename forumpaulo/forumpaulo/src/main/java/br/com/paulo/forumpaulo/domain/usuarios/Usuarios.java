package br.com.paulo.forumpaulo.domain.usuarios;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "Usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuarios implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String login;
    private String email;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuarios(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.login();
        this.senha = passEncode(dados.senha());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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

    private String passEncode(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(senha);
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
        if (dados.id() != null) {
            this.id = dados.id();
        }
        if (dados.ativo() != null) {
            this.ativo = dados.ativo();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.login() != null) {
            this.email = dados.email();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}




