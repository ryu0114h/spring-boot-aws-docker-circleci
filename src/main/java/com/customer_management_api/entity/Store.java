package com.customer_management_api.entity;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    Long id;

    @NotBlank(groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    @Size(min = 0, max = 50, groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    String name;

    @NotBlank(groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    @Size(min = 0, max = 200, groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    String description;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public Store(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public interface CreateStoreGroup {
    }

    public interface UpdateStoreGroup {
    }
}
