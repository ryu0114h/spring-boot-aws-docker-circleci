package com.customer_management_api.repository;

import com.customer_management_api.entity.Store;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface StoreMapper {

    @SelectProvider(StoreSqlProvider.class)
    List<Store> getStores();

    @SelectProvider(StoreSqlProvider.class)
    Optional<Store> getStore(Long id);

    @InsertProvider(StoreSqlProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createStore(Store store);

    @UpdateProvider(StoreSqlProvider.class)
    int updateStore(Store store);

    @DeleteProvider(StoreSqlProvider.class)
    int deleteStore(Long id);

    class StoreSqlProvider implements ProviderMethodResolver {
        public String getStores() {
            return new SQL() {{
                SELECT("*");
                FROM("STORE");
            }}.toString();
        }

        public String getStore(Long id) {
            return new SQL() {{
                SELECT("*");
                FROM("STORE");
                WHERE("ID = #{id}");
            }}.toString();
        }

        @Options(useGeneratedKeys = true)
        public String createStore(Store store) {
            return new SQL() {{
                INSERT_INTO("STORE");
                VALUES("NAME", "#{name}");
                VALUES("DESCRIPTION", "#{description}");
            }}.toString();
        }

        public String updateStore(Store store) {
            return new SQL() {{
                UPDATE("STORE");
                SET("NAME = #{name}");
                SET("DESCRIPTION = #{description}");
                WHERE("ID = #{id}");
            }}.toString();
        }

        public String deleteStore(Long id) {
            return new SQL() {{
                DELETE_FROM("STORE");
                WHERE("ID = #{id}");
            }}.toString();
        }
    }
}
