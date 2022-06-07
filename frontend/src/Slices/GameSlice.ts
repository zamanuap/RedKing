import React from 'react';
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

interface GameSliceState {
    gameStatus: string;
    dealerCount: number,    //dealer hand value
    playerCount: number,    //player hand value
    isDealersTurn: boolean,
    isDealerBusted: boolean,
    isPlayerBusted: boolean,
    winner: string
}

const initialGameState: GameSliceState = {
    gameStatus: 'Game not Initialized',
    dealerCount: 0,
    playerCount: 0,
    isDealersTurn: false,
    isDealerBusted: false,
    isPlayerBusted: false,
    winner: "none"
}


export const gameSlice = createSlice({
    name: 'game',
    initialState: initialGameState,
    reducers: {
        toggleDealerTurn: (state) => {
            state.isDealersTurn = !state.isDealersTurn;
        },
        toggleDealerBust: (state) => {
            state.isDealerBusted = !state.isDealerBusted;
        },
        togglePlayerBusted: (state) => {
            state.isPlayerBusted = !state.isPlayerBusted;
        },
        setWinner: (state, action) => {
            state.winner = action.payload;
        },
        setGameStatus: (state, action) => {
            state.gameStatus = action.payload;
        },
        setPlayerCount: (state, action)=>{
            state.playerCount = action.payload;
        },
        setDealerCount: (state, action)=>{
            state.dealerCount = action.payload;
        }
    }
});

export const {setDealerCount, setPlayerCount, setGameStatus, setWinner, toggleDealerBust, toggleDealerTurn, togglePlayerBusted} = gameSlice.actions;

export default gameSlice.reducer;
