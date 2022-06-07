import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { IUser } from '../../Interfaces/IUser';
import { AppDispatch, RootState } from '../../Store';
import '../HighScore/Scoreboard.css';

const ScoreRows: React.FC<IUser> = (user: IUser) => {
  const dispatch: AppDispatch = useDispatch();
  const userState = useSelector((state: RootState) => state.user);

  return (
    <tr className='rows'>
      <td>{user.firstName}</td>
      <td>{user.lastName}</td>
      <td>{user.email}</td>
      <td>${user.money?.toFixed(2)}</td>
    </tr>
  );
};

export default ScoreRows;
