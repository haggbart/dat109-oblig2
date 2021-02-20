//package no.hvl.dat109.bilutleie;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

//@SpringBootTest
//@AutoConfigureMockMvc
//class Casual {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void test() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        var now = LocalDateTime.now();
//        System.out.println(now.format(formatter));
//    }

//    @Test
//    public void offerselect() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/reservation/locationtime")
//                .accept(MediaType.TEXT_HTML)
//                .param("name", "test123")
//                .param("password", "pass"))
//                .andExpect(view().name("success"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}
