import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { setSliceMoney, toggleLock, userBet, withdrawMoney } from '../../Slices/UserSlice';
import { AppDispatch, RootState } from '../../Store';

import './Bet.css';

export const Bet: React.FC = () => {
  const userState = useSelector((state: RootState) => state.user);
  const bet = useSelector((state: RootState) => state.user.bet);
  const sliceMoney = useSelector((state: RootState) => state.user.sliceMoney);
  const [money, setMoney] = useState<string>('');
  const [clicked, setClicked] = useState(false);
  const dispatch: AppDispatch = useDispatch();
  const navigator = useNavigate();

  useEffect(() => {
    if(clicked){
      navigator("/playgame");
    }
    
  },[clicked]);

  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMoney(event.target.value);
  };

  const handleSubmit = (event: React.MouseEvent<HTMLInputElement>) => {
    
    setClicked(true);

    let amount = {
      userId: userState.user?.userId ? userState.user?.userId : 0,
      amount: parseFloat(money),
    };

    if(money.length != 0){
      dispatch(setSliceMoney(sliceMoney - parseFloat(money)));
    }
    
    if (userState && userState.user?.money > amount.amount) {
      dispatch(userBet(parseFloat(money)));
      console.log("Bets: ", userState.lockBet);
      //lock bets after user makes one
      if (!userState.lockBet) { //if bets are not locked
        dispatch(toggleLock());
      }
    } else {
      dispatch(userBet(0)); //do nothing
    }

    //navigator('/playgame');
  };

  const handleBack = (event: React.MouseEvent<HTMLInputElement>) => {
    navigator('/playgame');
  };

  return (
    <div className="moneyHomeScreen">
      <div className="btnContainer">
        <h3> How much you want to bet?</h3>
        <div className="moneyInputDiv">
          <input
            autoComplete="off"
            className="moneyInput"
            type="text"
            name="money"
            placeholder="Enter amount"
            onChange={handleInput}
          />
        </div>
        <div className="twoButtonContainer">
          <input
            type="button"
            className="withdrawBtn"
            id="WithdrawBtn"
            value="Bet"
            onClick={handleSubmit}
          />
          <input
            type="button"
            className="backButton"
            id="backButton"
            value="Back"
            onClick={handleBack}
          />
        </div>
      </div>
    </div>
  );
};
