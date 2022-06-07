import React, { useEffect } from 'react';
import './HomeScreen.css';

import { Link } from 'react-router-dom';

import { Navbar } from '../../Components/Navbar/Navbar';

// import { useSelector, useDispatch } from 'react-redux';
// import { RootState, AppDispatch } from '../../store';

// go inside App for Route
export const HomeScreen: React.FC = () => {
  return (
    <>
      {/* <Navbar /> */}
      <section className="homeScreen">
        <div className="logregContainer">
          <h3 className="blackingHeader">RedKing</h3>

          <div className="buttonContainer">
            <Link to="/user">
              <button className="registerButton">Register</button>
            </Link>
            <Link to="/login">
              <button className="loginButton">Login</button>
            </Link>
          </div>
        </div>
      </section>
    </>
  );
};
