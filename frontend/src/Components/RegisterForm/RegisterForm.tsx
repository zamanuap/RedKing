import React, { useState, useEffect } from 'react';
import './RegisterForm.css';

import { Link } from 'react-router-dom';

import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import { registerUser, sendMail, updateUser } from '../../Slices/UserSlice';
import { AppDispatch, RootState } from '../../Store';

// will go inside RegisterPage
export const RegisterForm: React.FC<any> = (spinner: any) => {
  const userInfo = useSelector((state: RootState) => state.user.user);
  console.log('coming from RegisterForm line 14 ', userInfo);

  const [firstName, setFirstName] = useState(
    userInfo !== undefined ? userInfo?.firstName : ''
  );
  const [lastName, setlastName] = useState(
    userInfo !== undefined ? userInfo?.lastName : ''
  );
  const [email, setEmail] = useState(
    userInfo !== undefined ? userInfo?.email : ''
  );
  const [password, setPassword] = useState(
    userInfo !== undefined ? userInfo?.password : ''
  );

  let userid = userInfo && userInfo.userId;

  // const [firstName, setFirstName] = useState<string>('');
  // const [lastName, setlastName] = useState<string>('');
  // const [email, setEmail] = useState<string>('');
  // const [password, setPassword] = useState<string>('');

  const dispatch: AppDispatch = useDispatch();
  const navigator = useNavigate();

  // input change handler
  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.name === 'firstname') {
      setFirstName(event.target.value);
    } else if (event.target.name === 'lastname') {
      setlastName(event.target.value);
    } else if (event.target.name === 'email') {
      setEmail(event.target.value);
    } else if (event.target.name === 'password') {
      setPassword(event.target.value);
    }
  };

  // form submit handler
  const handleRegister = (event: React.MouseEvent<HTMLButtonElement>) => {
    let credentials = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
    };
    console.log('coming from handleRegister line 41 ', credentials);

    dispatch(registerUser(credentials));
  };

  useEffect(() => {
    if (userInfo) {
      let data = {
        firstName: userInfo.firstName,
        email: userInfo.email,
        msgType: 'Register',
      };
      dispatch(sendMail(data));
      // navigator('/playgame');
    }
  }, [userInfo]);
  // form submit handler
  const handleUpdate = (event: React.MouseEvent<HTMLButtonElement>) => {
    let userUpdate = {
      userId: userid,
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
    };
    console.log('coming from handleUpdate line 74 ', userUpdate);

    dispatch(updateUser(userUpdate));
    navigator('/playgame');
  };

  return (
    <div className="register">
      {/* text container */}
      <div className="registerTextContainer">
        <h1 className="registerHeader">
          {userInfo !== undefined
            ? 'You may update your details'
            : 'Please Register Before You Play'}
        </h1>
      </div>

      <form className="registerForm">
        {/* for email */}
        <div className="inputDiv">
          <h4 className="inputH4">First Name</h4>

          <input
            autoComplete="off"
            className="registerInput"
            type="text"
            name="firstname"
            value={firstName}
            placeholder="first Name"
            onChange={handleInput}
          />
        </div>

        {/* for password */}
        <div className="inputDiv">
          <h4 className="inputH4">Last Name</h4>

          <input
            autoComplete="off"
            className="registerInput"
            type="text"
            name="lastname"
            value={lastName}
            placeholder="last Name"
            onChange={handleInput}
          />
        </div>

        {/* for password */}
        <div className="inputDiv">
          <h4 className="inputH4">Email</h4>

          <input
            autoComplete="off"
            className="registerInput"
            type="email"
            name="email"
            value={email}
            placeholder="email"
            onChange={handleInput}
          />
        </div>

        {/* for password */}
        <div className="inputDiv">
          <h4 className="inputH4">Password</h4>

          <input
            autoComplete="off"
            className="registerInput"
            type="password"
            name="password"
            value={password}
            placeholder="password"
            onChange={handleInput}
          />
        </div>
      </form>

      <button
        className="registerBtn"
        onClick={userInfo !== undefined ? handleUpdate : handleRegister}
      >
        {userInfo === undefined ? 'Register' : 'Update'}
      </button>

      <Link
        to={userInfo !== undefined ? '/playgame' : '/login'}
        className="backToGame"
      >
        {userInfo === undefined ? 'Already Registered?' : 'Back to Game'}
      </Link>
    </div>
  );
};
