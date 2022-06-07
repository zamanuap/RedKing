import React, { useEffect } from 'react'
import { Root } from 'react-dom/client';
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom';
import { getDealDealer, getDealPlayer, initializeDeck } from '../../Slices/DeckSlice';
import { setGameStatus, setWinner, toggleDealerBust, toggleDealerTurn, togglePlayerBusted } from '../../Slices/GameSlice';
import { AppDispatch, RootState } from '../../Store'

const StartGameButton: React.FC = () => {
  const userState = useSelector((state: RootState) => state.user);
  const deckState = useSelector((state: RootState) => state.deck);
  const gameState = useSelector((state: RootState) => state.game);

  const dispatch: AppDispatch = useDispatch();
  const navigator = useNavigate();

  useEffect(() => {
    if (deckState.startGame) {
      dispatch(setGameStatus('Game is Initialized')); //change the state of the game to initialized

      if (deckState.deck) {

        if (deckState.playerHand) {
          dispatch(getDealPlayer(deckState.deck.deckId));
          dispatch(getDealDealer(deckState.deck.deckId));
          dispatch(getDealPlayer(deckState.deck.deckId));
          dispatch(getDealDealer(deckState.deck.deckId));
        }

      }

    }

  }, [deckState.startGame, deckState.deck]);

  const handleInitGame = (event: React.MouseEvent<HTMLButtonElement>) => {
    if (!userState.user) { //if user does not exist, return to login screen
      navigator('/login');
    } else {
      dispatch(setWinner('none')); //reset winner status
      if(gameState.isDealersTurn) { //if it was the dealers turn for some reason, give the turn back to the player
        dispatch(toggleDealerTurn())
      }

      dispatch(initializeDeck(userState.user)); //initialize the game
    }
  }

  return (
    <>
      {deckState.loading == true
        ? <button className="start-game-btn" disabled={true} onClick={handleInitGame}>Start</button>
        : <button className="start-game-btn" onClick={handleInitGame}>Start</button>
      }
    </>

  )
}

export default StartGameButton
