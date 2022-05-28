package com.customer_management_api.entity;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Store {

    Long id;

    @NotBlank(groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    String name;

    @NotBlank(groups = {CreateStoreGroup.class, UpdateStoreGroup.class})
    String description;

    public interface CreateStoreGroup {
    }

    public interface UpdateStoreGroup {
    }
}
