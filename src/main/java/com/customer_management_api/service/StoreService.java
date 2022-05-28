package com.customer_management_api.service;

import com.customer_management_api.entity.Store;
import java.util.List;

public interface StoreService {

    List<Store> getStores();

    Store getStore(Long id);

    Store createStore(Store store);

    Store updateStore(Store store);

    void deleteStore(Long id);
}
