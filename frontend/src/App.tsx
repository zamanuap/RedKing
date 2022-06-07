import React from 'react';
import './App.css';

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import { HomeScreen } from './Views/HomeScreen/HomeScreen';
import { LoginPage } from './Views/LoginPage/LoginPage';
import { RegisterPage } from './Views/RegisterPage/RegisterPage';
import { PlayGamePage } from './Views/PlayGamePage/PlayGamePage';
import { MoneyPage } from './Views/MoneyPage/MoneyPage';
import HighScore from './Components/HighScore/HighScore';
import { BetPage } from './Views/BetPage/BetPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="*" element={<Navigate to="/login" replace />} />
        <Route path="/home" element={<HomeScreen />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/user" element={<RegisterPage />} />

        <Route path="/playgame" element={<PlayGamePage />} />
        <Route path="/money" element={<MoneyPage />} />
        <Route path="/bet" element={<BetPage />} />
        <Route path="/scores" element={<HighScore />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
