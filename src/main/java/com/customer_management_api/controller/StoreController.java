package com.customer_management_api.controller;

import com.customer_management_api.entity.Store;
import com.customer_management_api.service.StoreService;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getStores() {
        return storeService.getStores();
    }

    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeService.getStore(id);
    }

    @PostMapping
    public Store createStore(@RequestBody @Validated({Store.CreateStoreGroup.class}) Store store) {
        return storeService.createStore(store);
    }

    @PatchMapping("/{id}")
    public Store updateStore(@PathVariable Long id,
                             @RequestBody @Validated({Store.UpdateStoreGroup.class}) Store store) {
        store.setId(id);
        return storeService.updateStore(store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
