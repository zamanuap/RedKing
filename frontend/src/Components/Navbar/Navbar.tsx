import React, { useEffect } from 'react';
import './Navbar.css';
import { LogOutButton } from '../LogOutButton/LogOutButton';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { AppDispatch, RootState } from '../../Store';

// inside PlayGamePage
export const Navbar: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  // const navigator = useNavigate();

  const userInfo = useSelector((state: RootState) => state.user.user);
  const userState = useSelector((state: RootState) => state.user);
  const gameState = useSelector((state: RootState) => state.game);

  return (
    <nav className="navBar">
      <div className="navMenu">
        <Link to="/user">
          <p>{userInfo ? userInfo.firstName : 'Anonymous'}</p>
        </Link>
        <div className="moneyContainer">
          <p>{userInfo ? `$${userInfo.money}` : '$0.00'}</p>
          <Link to="/money">
            <button className="moneyBtn">Money</button>
          </Link>

          <p>{userInfo ? `$${userState.bet}` : '$0.00'}</p>
          {gameState.gameStatus.includes('Game not Initialized') || gameState.isDealersTurn? (
            <Link to="/bet">
              <button className="betBtn">Bet</button>
            </Link>
          ) : (
            // <p className="betsLocked">Bets Are Locked!</p>
            <Link to="/bet">
              <button className="betnBtnDisabled" disabled={true}>
                Bet's Locked
              </button>
            </Link>
          )}

          <LogOutButton />
        </div>
      </div>
    </nav>
  );
};
