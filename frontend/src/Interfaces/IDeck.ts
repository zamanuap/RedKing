import { ICard } from './ICard';
import { IUser } from './IUser';

export interface IDeck {
  deckId?: number;
  user?: IUser;
  cards?: ICard[];
  size?: number;
}
