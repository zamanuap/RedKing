export interface ICard {
  id?: number;
  rank: Rank | string;
  suit: Suit | string;
}

export enum Rank {
  ACE = 0,
  TWO = 1,
  THREE = 2,
  FOUR = 3,
  FIVE = 4,
  SIX = 5,
  SEVEN = 6,
  EIGHT = 7,
  NINE = 8,
  TEN = 9,
  JACK = 10,
  QUEEN = 11,
  KING = 12,
}

export enum Suit {
  SPADES = 0,
  HEARTS = 1,
  CLOVERS = 2,
  DIAMONDS = 3,
}
