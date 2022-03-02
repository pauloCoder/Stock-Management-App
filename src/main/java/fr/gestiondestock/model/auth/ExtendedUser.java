package fr.gestiondestock.model.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ExtendedUser extends User {

    @Getter
    @Setter
    private Integer idEntreprise;

    public ExtendedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ExtendedUser(String username, String password, Integer idEntreprise , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.idEntreprise = idEntreprise;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
