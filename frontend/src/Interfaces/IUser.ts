export interface IUser {
  userId: number;
  email: string;
  firstName: string;
  lastName: string;
  password?: string;
  money?: number | any;
}
