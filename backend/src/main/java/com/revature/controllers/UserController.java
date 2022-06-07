package com.revature.controllers;

import java.util.List;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.InvalidDepositAmount;
import com.revature.exceptions.InvalidEmailOrPasswordException;
import com.revature.exceptions.UserEmailAlreadyExistsException;
import com.revature.models.DepositHelper;
import com.revature.models.LoginHelper;
import com.revature.models.User;
import com.revature.models.WithdrawHelper;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    private UserService us;

    @Autowired
    public UserController( UserService us ) {
        this.us = us;
    }

    @PostMapping("/register")
    @ResponseBody
    public User handleRegister( @RequestBody User rbUser ) throws UserEmailAlreadyExistsException {
        return us.registerUser(rbUser.getEmail(), rbUser.getPassword(), rbUser.getFirstName(), rbUser.getLastName(), rbUser.getMoney());
    }

    @PostMapping("/login")
    @ResponseBody
    public User handleLogin( @RequestBody LoginHelper lh ) throws InvalidEmailOrPasswordException {
        return us.loginUser(lh.getEmail(), lh.getPassword());
    }

    @PostMapping("/update")
    @ResponseBody
    public User handleUpdate ( @RequestBody User newUser ) {
        return us.updateUser( newUser );
    }

    @PostMapping("/deposit")
    @ResponseBody
    public User handleDeposit ( @RequestBody DepositHelper dh ) throws InvalidDepositAmount {
        return us.deposit( dh );
    }

    @GetMapping("/allUsers")
    @ResponseBody
    public List<User> handleUserScores ( ) {
        return us.retrieveIdAndScore();
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public User handleWithdraw ( @RequestBody WithdrawHelper wh) throws InsufficientFundsException {
        return us.withdraw(wh);
    }
}
