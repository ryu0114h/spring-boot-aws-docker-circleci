package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    List<Store> getStores();

    Optional<Store> getStore(Long id);

    Store createStore(Store store);

    Store updateStore(Store store);

    void deleteStore(Long id);
}
