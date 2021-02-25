package no.hvl.dat109.bilutleie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void adminWithoutAuth() throws Exception {
        mockMvc.perform(get("/admin/reservations"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser("admin")
    @Test
    public void adminWithAuth() throws Exception {
        mockMvc.perform(get("/admin/reservations"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginWithValidUser() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("admin")
                .password("epic");
        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("admin"));
    }

    @Test
    public void loginWithInvalidUser() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("admin")
                .password("fail");
        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    public void homePageResponseIsOk() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print()).andExpect(status().isOk());
    }
}
