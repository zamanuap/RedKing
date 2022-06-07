import { configureStore, combineReducers } from '@reduxjs/toolkit';

import storage from 'redux-persist/lib/storage';

import {
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from 'redux-persist';

//We would import our reducers from the slices we have to create
import userReducer from './Slices/UserSlice';
import deckReducer from './Slices/DeckSlice';
import gameReducer from './Slices/GameSlice';
//store.js
const persistConfig = {
  key: 'root',
  storage,
};

const reducers = combineReducers({
  user: userReducer,
  deck: deckReducer,
  game: gameReducer
});

// const persistedReducer = persistReducer(persistConfig, reducers);

// export const Store = configureStore({
//   reducer: persistedReducer,
//   middleware: (getDefaultMiddleware) =>
//     getDefaultMiddleware({
//       serializableCheck: {
//         ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
//       },
//     }),
// });

// inside index
export const Store = configureStore({
  reducer: {
    user: userReducer,
    deck: deckReducer,
    game: gameReducer
  },
});

//We must export these two things to make our lives easier later
export type RootState = ReturnType<typeof Store.getState>;
export type AppDispatch = typeof Store.dispatch;
