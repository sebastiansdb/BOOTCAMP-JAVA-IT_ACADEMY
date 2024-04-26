package com.S05T02N123.security;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "users")
public class User implements UserDetails {
    @Id
    private String playerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> playersIds = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;

//    public void setPlayer(PlayerEntity playerEntity){
//        playerEntityList.add(playerEntity);
//    }

    public void setPlayersIds (Long id){
        int index = playersIds.size();
        playersIds.add(index,id);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
}
