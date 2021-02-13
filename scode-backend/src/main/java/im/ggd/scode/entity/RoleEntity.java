package im.ggd.scode.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEntity implements GrantedAuthority {
    ADMIN, CIVILIAN;

    @Override
    public String getAuthority() {
        return name();
    }

}
