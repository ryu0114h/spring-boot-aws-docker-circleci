package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreRepositoryImpl(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public List<Store> getStores() {
        return storeMapper.getStores();
    }

    @Override
    public Optional<Store> getStore(Long id) {
        return storeMapper.getStore(id);
    }

    @Override
    public Store createStore(Store store) {
        return storeMapper.createStore(store);
    }

    @Override
    public Store updateStore(Store store) {
        return storeMapper.updateStore(store);
    }

    @Override
    public void deleteStore(Long id) {
        storeMapper.deleteStore(id);
    }
}
