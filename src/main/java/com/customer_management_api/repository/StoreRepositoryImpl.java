package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;

public interface StoreRepository {
    
    List<Store> getStores();
}
