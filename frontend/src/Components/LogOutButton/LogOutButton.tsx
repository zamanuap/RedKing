import React from 'react';
import './LogOutButton.css';

import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../../Slices/UserSlice';
import { AppDispatch, RootState } from '../../Store';

export const LogOutButton: React.FC = () => {
  const userInfo = useSelector((state: RootState) => state.user);
  const deckInfo = useSelector((state: RootState) => state.deck);

  const dispatch: AppDispatch = useDispatch();
  const navigator = useNavigate();

  const handleLogOut = () => {
    dispatch(logoutUser());
    navigator('/login');
  };

  return (
    <>
      <button className="logoutBtn" onClick={handleLogOut}>
        Logout
      </button>
    </>
  );
};
