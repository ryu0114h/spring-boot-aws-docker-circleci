package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface StaffMapper {

    @SelectProvider(StaffSqlProvider.class)
    List<Staff> getStaffs();

    @SelectProvider(StaffSqlProvider.class)
    Optional<Staff> getStaff(Long id);

    @SelectProvider(StaffSqlProvider.class)
    Optional<Staff> getStaffByEmail(String email);

    @SelectProvider(StaffSqlProvider.class)
    Staff createStaff(Staff staff);

    @SelectProvider(StaffSqlProvider.class)
    Staff updateStaff(Staff staff);

    @SelectProvider(StaffSqlProvider.class)
    void deleteStaff(Long id);

    class StaffSqlProvider implements ProviderMethodResolver {
        public String getStaffs() {
            return new SQL() {{
                SELECT("*");
                FROM("STAFF");
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
