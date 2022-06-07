import React, { useEffect } from 'react'
import { Root } from 'react-dom/client';
import { useDispatch, useSelector } from 'react-redux';
import { getDealDealer, getDealPlayer, initializeDeck, toggleStartGame } from '../../Slices/DeckSlice';
import { setWinner, toggleDealerBust, toggleDealerTurn, togglePlayerBusted } from '../../Slices/GameSlice';
import { clearHands } from '../../Slices/DeckSlice';
import { AppDispatch, RootState } from '../../Store';

const NextRound: React.FC = () => {
    const gameState = useSelector((state: RootState) => state.game);
    const deckState = useSelector((state: RootState) => state.deck);
    const userState = useSelector((state: RootState) => state.user);
    const dispatch: AppDispatch = useDispatch();

    //when both hands are dealt their initial 2 cards, reset gamestates to enable the hit and stand button
    useEffect(() => {
        if (deckState.playerHand && deckState.dealerHand) { //get past typescript checks
            if (deckState.playerHand.length == 2 && deckState.dealerHand.length == 2) {

                console.log("NOW dealer's turn: ", gameState.isDealersTurn);
                if (gameState.isPlayerBusted == true) { //reset bust status
                    dispatch(togglePlayerBusted());

                } else if (gameState.isDealerBusted == true) {
                    dispatch(toggleDealerBust());
                }
            }
        }
    }, [deckState.playerHand, deckState.dealerHand])



    useEffect(() => {
        //if hands are empty, then deal the initial 4 cards
        if (deckState.playerHand && deckState.playerHand?.length == 0 && gameState.winner.includes("none") && deckState.startGame == false) {
            dispatch(getDealPlayer(deckState.deck?.deckId));
            dispatch(getDealPlayer(deckState.deck?.deckId));
            dispatch(getDealDealer(deckState.deck?.deckId));
            dispatch(getDealDealer(deckState.deck?.deckId));
        }
    }, [deckState.playerHand, deckState.dealerHand]);

    const handleNext = () => { //clearplayer hands

        if (gameState.isDealersTurn) {
            dispatch(toggleDealerTurn());
        } // if it is dealers turn, change it back to players turn
        
        if (deckState.startGame) {
            dispatch(toggleStartGame());
        }
        //reset deck if deck of cards read < 10 cards
        if (userState.user) {
            dispatch(initializeDeck(userState.user));
        }

        dispatch(setWinner("none"));
        dispatch(clearHands());
    }

    return (
        <>
            {
                gameState.winner.includes("none")
                    ? <button disabled={true}>Next</button>
                    : <button onClick={handleNext}>Next</button>
            }
        </>
    )
}

export default NextRound