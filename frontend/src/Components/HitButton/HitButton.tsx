import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getDealPlayer } from '../../Slices/DeckSlice';
import { toggleDealerTurn, togglePlayerBusted } from '../../Slices/GameSlice';
import { AppDispatch, RootState } from '../../Store';

import { calcHandValue } from '../ValueCounter/ValueCounter';

export const HitButton: React.FC = () => {
  const userState = useSelector((state: RootState) => state.user);
  const deckState = useSelector((state: RootState) => state.deck);
  const gameState = useSelector((state: RootState) => state.game);

  const playerHand = useSelector((state: RootState) => state.deck.playerHand);
  const dealerHand = useSelector((state: RootState) => state.deck.dealerHand);
  
  const dispatch: AppDispatch = useDispatch();

  useEffect(() => {
    if (calcHandValue(playerHand) > 21) { //player busts, dealer automatically win
      dispatch(togglePlayerBusted());
      dispatch(toggleDealerTurn()); //dealers turn to draw cards
      
    } else if (calcHandValue(playerHand) == 21) { //player gets 21 TODO: turn should switch to dealer before setting game winner to player
      //dispatch(setWinner("player")); //set winner to player
    } //TODO: Add condition where player stands
  }, [playerHand]);

  const handleHitButton = () => {
    dispatch(getDealPlayer(deckState.deck?.deckId));
  };

  return (
    <>
      {gameState.isPlayerBusted == true || !gameState.winner.includes("none") || gameState.isDealersTurn
        ? <button className="hit-button" disabled={true} onClick={handleHitButton}>
          Hit!
        </button>
       : (
        <button className="hit-button" onClick={handleHitButton}>
          Hit!
        </button>
      )}
    </>
  );
};
