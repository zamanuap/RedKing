package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.InvalidDepositAmount;
import com.revature.exceptions.InvalidEmailOrPasswordException;
import com.revature.exceptions.UserEmailAlreadyExistsException;
import com.revature.models.DepositHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.User;
import com.revature.models.WithdrawHelper;
import com.revature.repository.UserRepo;
import com.revature.services.UserService;


import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class UserServiceTest {

    @Mock
    public static UserRepo ur;

    @Autowired
    public static UserService us;

    @Test
    public void testRegisterUser() throws UserEmailAlreadyExistsException {
        us = new UserService(ur);
        User u = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);
        when(ur.save(Mockito.any())).thenReturn(u);

        Assertions.assertEquals(u, us.registerUser("test@gmail.com", "test_first", "test_last", "test_password", 0));
    }

    @Test
    public void testRegisterUserException() throws UserEmailAlreadyExistsException {
        us = new UserService(ur);
        User u = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);
        when(ur.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(u);

        Assertions.assertThrows(UserEmailAlreadyExistsException.class, () -> {
            us.registerUser("test@gmail.com", "test_first", "test_last", "test_password", 0);
        });
    }

    @Test
    public void testLoginUser() throws InvalidEmailOrPasswordException {
        us = new UserService(ur);
        User user = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);

        when(ur.findByEmailAndPassword( Mockito.anyString(), Mockito.anyString())).thenReturn(user);
        User testUser = us.loginUser("test@gmail.com", "test_password");
        Assertions.assertEquals(user, testUser);
    }

    @Test
    public void testLoginUserException() throws InvalidEmailOrPasswordException {
        us = new UserService(ur);

        when(ur.findByEmailAndPassword( Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(InvalidEmailOrPasswordException.class, () -> {
            us.loginUser("test@gmail.com", "test_password");
        });
    }

    @Test
    public void testUpdateUser() {
        us = new UserService(ur);
        User user = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);
        user.setUserId(1);

        Mockito.when(ur.save(Mockito.any())).thenReturn(user);
        Assertions.assertEquals(user, us.updateUser(user));
    }

    @Test
    public void testDeposit () throws InvalidDepositAmount {
        us = new UserService(ur);
        User user = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);
        user.setUserId(1);

        DepositHelper dh = new DepositHelper(1, 50);

        Mockito.when(ur.findByUserId(Mockito.anyInt())).thenReturn(user);
        Mockito.when(ur.save(Mockito.any())).thenReturn(user);
        Assertions.assertEquals(50, us.deposit(dh).getMoney());

    }

    @Test
    public void testDepositException () throws InvalidDepositAmount {
        us = new UserService(ur);
        User u = new User("test@gmail.com", "test_first", "test_last", "test_password", 0);
        u.setUserId(1);

        DepositHelper dh = new DepositHelper(1, -1);
        Mockito.when(ur.findByUserId(Mockito.anyInt())).thenReturn(u);
        Mockito.when(ur.save(Mockito.any())).thenReturn(u);

        Assertions.assertThrows(InvalidDepositAmount.class, () -> {
            us.deposit( dh );
        });
    }

    @Test
    public void testRetrieveAllUsers () { //Tests if all users were retrieved
        us = new UserService(ur);

        /*2  test users with passwords*/
        User t1 = new User("t1@email.com", "t1", "lastT1", "t1-password", 86);
        User t2 = new User("t2@email.com", "t2", "lastT2", "t2-password", 68);

        /*mock list*/
        List<User> ul = new ArrayList<>();

        ul.add(t1);
        ul.add(t2);

        /*Mockito & Junit test*/
        Mockito.when(ur.findAll()).thenReturn(ul);

        Assertions.assertEquals(2, us.retrieveIdAndScore().size());
    }

    @Test
    public void testWithdraw () throws InsufficientFundsException {
        us = new UserService(ur);

        WithdrawHelper wh = new WithdrawHelper();
        wh.setUserId(1);
        wh.setAmount(1.00);

        User t1 = new User("t1@email.com", "t1", "lastT1", "t1-password", 86);
        t1.setUserId(1);

        Mockito.when(ur.findByUserId(wh.getUserId())).thenReturn(t1);
        Assertions.assertEquals(85, us.withdraw(wh).getMoney());
    }

    @Test
    public void testWithdrawException () throws InsufficientFundsException {
        us = new UserService(ur);

        WithdrawHelper wh = new WithdrawHelper();
        wh.setUserId(1);
        wh.setAmount(6.00);

        User t1 = new User("t1@email.com", "t1", "lastT1", "t1-password", 5);
        t1.setUserId(1);

        Mockito.when(ur.findByUserId(wh.getUserId())).thenReturn(t1);
        Assertions.assertThrows(InsufficientFundsException.class, () -> {
            us.withdraw(wh);
        });
    }
}
