package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface StoreMapper {

    List<Store> getStores();

    Optional<Store> getStore(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createStore(Store store);

    int updateStore(Store store);

    int deleteStore(Long id);
}
