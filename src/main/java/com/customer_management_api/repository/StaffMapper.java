package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
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
public interface StaffMapper {

    @SelectProvider(StaffSqlProvider.class)
    List<Staff> getStaffs(StaffSelector selector);

    @SelectProvider(StaffSqlProvider.class)
    Optional<Staff> getStaff(Long id);

    @SelectProvider(StaffSqlProvider.class)
    Optional<Staff> getStaffByEmail(String email);

    @InsertProvider(StaffSqlProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createStaff(Staff staff);

    @UpdateProvider(StaffSqlProvider.class)
    int updateStaff(Staff staff);

    @DeleteProvider(StaffSqlProvider.class)
    int deleteStaff(Long id);

    class StaffSqlProvider implements ProviderMethodResolver {
        public String getStaffs(StaffSelector selector) {
            return new SQL() {{
                SELECT("*");
                FROM("STAFF");
                WHERE("STORE_ID = #{storeId}");
            }}.toString();
        }

        public String getStaff(Long id) {
            return new SQL() {{
                SELECT("*");
                FROM("STAFF");
                WHERE("ID = #{id}");
            }}.toString();
        }

        public String getStaffByEmail(String email) {
            return new SQL() {{
                SELECT("*");
                FROM("STAFF");
                WHERE("EMAIL = #{email}");
            }}.toString();
        }

        public String createStaff(Staff staff) {
            return new SQL() {{
                INSERT_INTO("STAFF");
                VALUES("EMAIL", "#{email}");
                VALUES("PASSWORD", "#{password}");
                VALUES("LAST_NAME", "#{lastName}");
                VALUES("FIRST_NAME", "#{firstName}");
                VALUES("BIRTHDAY", "#{birthday}");
                VALUES("STORE_ID", "#{storeId}");
            }}.toString();
        }

        public String updateStaff(Staff staff) {
            return new SQL() {{
                UPDATE("STAFF");
                SET("EMAIL = #{email}");
                SET("PASSWORD = #{password}");
                SET("LAST_NAME = #{lastName}");
                SET("FIRST_NAME = #{firstName}");
                SET("BIRTHDAY = #{birthday}");
                WHERE("ID = #{id}");
            }}.toString();
        }

        public String deleteStaff(Long id) {
            return new SQL() {{
                DELETE_FROM("STAFF");
                WHERE("ID = #{id}");
            }}.toString();
        }
    }
}
