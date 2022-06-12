package com.customer_management_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    Long id;

    @JsonIgnore
    Long storeId;

    Store store;

    @NotBlank(groups = {CreateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    private String lastName;

    @NotBlank(groups = {CreateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    private String firstName;

    @Email(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @NotBlank(groups = {CreateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = {CreateStaffGroup.class})
    private String password;

    @NotNull(groups = {CreateStaffGroup.class})
    private LocalDate birthday;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Staff(String lastName, String firstName, LocalDate birthday, String email, String password, Long storeId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.storeId = storeId;
    }

    public interface CreateStaffGroup {
    }

    public interface UpdateStaffGroup {
    }
}
