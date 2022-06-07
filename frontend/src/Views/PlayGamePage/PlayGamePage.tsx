import React from 'react';
import { useNavigate } from 'react-router-dom';
import HighScore from '../../Components/HighScore/HighScore';
import { Navbar } from '../../Components/Navbar/Navbar';
import { PlayGame } from '../../Components/PlayGame/PlayGame';

export const PlayGamePage: React.FC = () => {
  return (
    <>
      <Navbar />
      <PlayGame />
    </>
  );
};
