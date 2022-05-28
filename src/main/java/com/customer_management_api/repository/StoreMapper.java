package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

    //    @Select("SELECT * FROM STORE")
    List<Store> getStores();
}
