package com.customer_management_api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import com.customer_management_api.security.LoginStaff;
import com.customer_management_api.service.StaffService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class StaffControllerTest {

    private final HandlerMethodArgumentResolver LoginStaffArgumentResolver = new HandlerMethodArgumentResolver() {
        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.getParameterType().isAssignableFrom(LoginStaff.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
                throws Exception {
            Staff staff = new Staff("テスト", "太郎", LocalDate.now(), "test@example.com", "password", 1L);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("STAFF_ROLE"));
            return new LoginStaff(staff, authorities);
        }
    };

    private MockMvc mockMvc;

    @InjectMocks
    private StaffController staffController;

    @Mock
    private StaffService staffService;

    private AutoCloseable mock;

    @BeforeEach
    public void before() {
        mock = MockitoAnnotations.openMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(staffController)
                .setCustomArgumentResolvers(LoginStaffArgumentResolver)
                .build();
    }

    @AfterEach
    public void after() throws Exception {
        mock.close();
    }

    @Test
    @WithMockUser(username = "test@example.com")
    public void testGetStaffs() throws Exception {
        String lastName = "テスト";
        String firstName = "テスト";
        LocalDate birthday = LocalDate.now();
        String email = "test2@example.com";
        String password = "password";
        Long storeId = 1L;
        Staff staff = new Staff(lastName, firstName, birthday, email, password, storeId);

        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff);

        ArgumentMatcher<StaffSelector> matcher = argument -> {
            Assertions.assertEquals(storeId, argument.getStoreId());
            return true;
        };

        Mockito.doReturn(staffList).when(staffService).getStaffs(Mockito.argThat(matcher));

        mockMvc.perform(MockMvcRequestBuilders.get("/staffs"))
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(staffService, Mockito.times(1)).getStaffs(Mockito.argThat(matcher));
    }
}
