import { userInfo } from 'os';
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { initializeDeck } from '../../Slices/DeckSlice';
import { AppDispatch, RootState } from '../../Store';

const InitializeDeck: React.FC = () => {
  const userState = useSelector((state: RootState) => state.user);
  const dispatch: AppDispatch = useDispatch();

  const handleStart = () => {
    let userInfo = {
      userId: userState.user!.userId,
      email: userState.user!.email,
      firstName: userState.user!.firstName,
      lastName: userState.user!.lastName,
      money: userState.user!.money,
    };

    if (userState.user != null) {
      userInfo.email = userState.user!.email;
    }

    dispatch(initializeDeck(userInfo));
  };

  return (
    <button className="Start Game" onClick={handleStart}>
      Start Game
    </button>
  );
};

export default InitializeDeck;
