import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getDealPlayer } from "../../Slices/DeckSlice";
import { toggleDealerTurn } from "../../Slices/GameSlice";
import { AppDispatch, RootState } from "../../Store";


export const StandButton: React.FC = () => {

  const userState = useSelector((state: RootState) => state.user);
  const deckState = useSelector((state: RootState) => state.deck);
  const gameState = useSelector((state: RootState) => state.game);

  const playerHand = useSelector((state: RootState) => state.deck.playerHand);
  const dealerHand = useSelector((state: RootState) => state.deck.dealerHand);

  const dispatch: AppDispatch = useDispatch();

  //set to dealer turn
  const handleStandButton = () => {
      //give turn to the dealer
      dispatch(toggleDealerTurn());
  }

  return (
    <>
      {gameState.isPlayerBusted == true || gameState.winner != "none" || gameState.isDealersTurn? <button disabled={true} className="stand-button" >Stand.</button>
        : <button className="stand-button" onClick={handleStandButton}>Stand.</button>}
    </>
  )
}