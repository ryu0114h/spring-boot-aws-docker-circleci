package com.customer_management_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements UserDetails {

    Long id;

    Long storeId;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String lastName;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String firstName;

    @Email(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    String password;

    @NotNull(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    LocalDate birthday;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public Staff(String lastName, String firstName, LocalDate birthday, String email, String password, Long storeId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.storeId = storeId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public interface CreateStaffGroup {
    }

    public interface UpdateStaffGroup {
    }
}
