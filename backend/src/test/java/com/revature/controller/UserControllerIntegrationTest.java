package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.BlackJackAppApplication;
import com.revature.models.DepositHelper;
import com.revature.models.LoginHelper;
import com.revature.models.User;
import com.revature.models.WithdrawHelper;
import com.revature.repository.UserRepo;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BlackJackAppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo ur;

    private ObjectMapper om = new ObjectMapper();



    @Test
    public void testRegisterHandler() throws Exception {
        User testUser = new User("test@email.com", "test_first", "test_last", "test_password", 8);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(testUser))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.firstName").value("test_first"))
                .andExpect(jsonPath("$.lastName").value("test_last"))
                .andExpect(jsonPath("$.password").value("test_password"))
                .andExpect(jsonPath("$.money").value(8));

        User res = ur.findByEmailAndPassword("test@email.com", "test_password");

        Assertions.assertEquals("test_first", res.getFirstName());
        Assertions.assertEquals("test_last", res.getLastName());
        Assertions.assertEquals("test_password", res.getPassword());
    }

    @Test
    @Transactional
    public void testLoginHandler() throws Exception {
        User testUser = new User("test@email.com", "test_first", "test_last", "test_password", 8);
        ur.save(testUser);

        LoginHelper lh = new LoginHelper("test@email.com", "test_password");
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(lh))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.firstName").value("test_first"))
                .andExpect(jsonPath("$.lastName").value("test_last"))
                .andExpect(jsonPath("$.password").value("test_password"))
                .andExpect(jsonPath("$.money").value(8));

        User res = ur.findByEmailAndPassword("test@email.com", "test_password");

        Assertions.assertEquals("test_first", res.getFirstName());
        Assertions.assertEquals("test_last", res.getLastName());
        Assertions.assertEquals("test_password", res.getPassword());
    }

    @Test
    @Transactional
    public void testUpdateUser() throws Exception {
        User testUserA = new User("testA@email.com", "test_first", "test_last", "test_password", 8);
        testUserA.setUserId(0);

        User testUserB = new User("testB@email.com", "test_first_change", "test_last_change", "test_password", 8);
        testUserB.setUserId(0);

        ur.save(testUserA);

        mockMvc.perform(post("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(testUserB))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testB@email.com"))
                .andExpect(jsonPath("$.firstName").value("test_first_change"))
                .andExpect(jsonPath("$.lastName").value("test_last_change"))
                .andExpect(jsonPath("$.password").value("test_password"))
                .andExpect(jsonPath("$.money").value(8));

        User res = ur.findByEmailAndPassword("testB@email.com", "test_password");

        Assertions.assertEquals("test_first_change", res.getFirstName());
        Assertions.assertEquals("test_last_change", res.getLastName());
    }

    @Test
    @Transactional
    public void testGetAllUsers() throws Exception {
        User testUserA = new User("testA@email.com", "test_first", "test_last", "test_password", 8);
        testUserA.setUserId(0);

        User testUserB = new User("testB@email.com", "test_first_change", "test_last_change", "test_password", 8);
        testUserB.setUserId(1);

        ur.save(testUserB);
        ur.save(testUserA);

        List<User> testList = new ArrayList<>();

        mockMvc.perform(get("/user/allUsers"))
                .andDo(print())
                .andExpect(status().isOk());

        testList = ur.findAll();

        Assertions.assertEquals(2, testList.size());
    }

    @Test
    public void testDeposit() throws Exception {
        User testUserA = new User("testA@email.com", "test_first", "test_last", "test_password", 8);
        testUserA.setUserId(1);

        DepositHelper dh = new DepositHelper(1, 117);

        ur.save(testUserA);

        mockMvc.perform(post("/user/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(dh))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testA@email.com"))
                .andExpect(jsonPath("$.firstName").value("test_first"))
                .andExpect(jsonPath("$.lastName").value("test_last"))
                .andExpect(jsonPath("$.password").value("test_password"))
                .andExpect(jsonPath("$.money").value(125));
    }

    @Test
    public void testWithdraw() throws Exception {
        User testUserA = new User("testA@email.com", "test_first", "test_last", "test_password", 500);
        testUserA.setUserId(1);

        WithdrawHelper wh = new WithdrawHelper(1, 15.2);

        ur.save(testUserA);

        mockMvc.perform(post("/user/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(wh))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testA@email.com"))
                .andExpect(jsonPath("$.firstName").value("test_first"))
                .andExpect(jsonPath("$.lastName").value("test_last"))
                .andExpect(jsonPath("$.password").value("test_password"))
                .andExpect(jsonPath("$.money").value(484.8));
    }
}
