package com.customer_management_api.service;

import com.customer_management_api.entity.Store;
import com.customer_management_api.repository.StoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getStores() {
        return storeRepository.getStores();
    }

    @Override
    public Store getStore(Long id) {
        Optional<Store> optionalStore = storeRepository.getStore(id);
        if (optionalStore.isEmpty()) {
            throw new RuntimeException("店舗が存在しません。");
        }
        return optionalStore.get();
    }

    @Override
    public Store createStore(Store store) {
        storeRepository.createStore(store);
        Store newStore = getStore(store.getId());
        return newStore;
    }

    @Override
    public Store updateStore(Store store) {
        return storeRepository.updateStore(store);
    }

    @Override
    public void deleteStore(Long id) {
        getStore(id);
        storeRepository.deleteStore(id);
    }
}
