import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import React from 'react';
import axios from 'axios';
import { IUser } from '../Interfaces/IUser';

interface UserSliceState {
  loading: boolean;
  error: boolean;
  user?: IUser;
  users?: IUser[];
  sliceMoney: number;
  bet: number;
  lockBet: boolean;
}

const initialUserState: UserSliceState = {
  lockBet: false,
  error: false,
  loading: true,
  bet: 0,
  sliceMoney: 0
};

type Login = {
  email: string;
  password: string;
};

export const loginUser = createAsyncThunk(
  'user/login',
  async (credentials: Login, thunkAPI) => {
    try {
      const res = await axios.post(
        'http://18.191.118.66:8000/user/login',
        credentials
      );

      return {
        userId: res.data.userId,
        email: res.data.email,
        password: res.data.password,
        firstName: res.data.firstName,
        lastName: res.data.lastName,
        money: res.data.money,
      };
    } catch (e) {
      return thunkAPI.rejectWithValue('something went wrong');
    }
  }
);

type Register = {
  email: string | undefined;
  password: string | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
};

type ManageMoney = {
  userId: number;
  amount: number;
};

type Mail = {
  firstName: string;
  email: string;
  msgType: string;
};

// called from LoginForm component
export const registerUser = createAsyncThunk(
  'user/register',
  async (credentials: Register, thunkAPI) => {
    try {
      const res = await axios.post(
        'http://18.191.118.66:8000/user/register',
        credentials
      );

      console.log('coming from registerUser async line 59 ', res.data);

      return res.data;
    } catch (e) {
      return thunkAPI.rejectWithValue('something went wrong');
    }
  }
);

type Update = {
  userId: number | undefined;
  email: string | undefined;
  password: string | undefined;
  firstName: string | undefined;
  lastName: string | undefined;
};

// called from RegisterForm component
export const updateUser = createAsyncThunk(
  'user/updateuser',
  async (credentials: Update, thunkAPI) => {
    try {
      // axios.defaults.withCredentials = true;
      const res = await axios.post(
        'http://18.191.118.66:8000/user/update',
        credentials
      );

      console.log('coming from updateUser async line 103 ', res.data);

      return res.data;
    } catch (e) {
      return thunkAPI.rejectWithValue('something went wrong');
    }
  }
);

export const depositMoney = createAsyncThunk(
  'user/deposit',
  async (amount: ManageMoney, thunkAPI) => {
    try {
      // axios.defaults.withCredentials = true;
      const res = await axios.post(
        'http://18.191.118.66:8000/user/deposit',
        amount
      );
      return res.data;
    } catch (e) {
      return thunkAPI.rejectWithValue('something went wrong');
    }
  }
);

export const withdrawMoney = createAsyncThunk(
  'user/withdraw',
  async (amount: ManageMoney, thunkAPI) => {
    try {
      const res = await axios.post(
        'http://18.191.118.66:8000/user/withdraw',
        amount
      );
      return res.data;
    } catch (e) {
      return thunkAPI.rejectWithValue('something went wrong');
    }
  }
);

export const retrieveUserScores = createAsyncThunk(
  'user/scores',
  async (thunkAPI) => {
    try {
      const res = await axios.get('http://18.191.118.66:8000/user/allUsers');
      return res.data;
    } catch (e) {
      console.log('Some Error');
    }
  }
);

export const sendMail = createAsyncThunk(
  'user/mail',
  async (data: Mail, thunkAPI) => {
    try {
      const res = await axios.post('http://18.191.118.66:8000/mail', data);
      return res.data;
    } catch (e) {
      console.log('Some Error');
    }
  }
);

// //Create the slice
export const UserSlice = createSlice({
  name: 'user',
  initialState: initialUserState,
  reducers: {
    toggleError: (state) => {
      //   state.error = !state.error;
    },
    logoutUser: (state) => {
      state.user = undefined;
    },
    setSliceMoney: (state, action) => {
      state.sliceMoney = action.payload;
    },
    userBet: (state, action) => {
      state.bet = action.payload;
    },
    toggleLock: (state) => {
      state.lockBet = !state.lockBet;
    }
  },

  extraReducers: (builder) => {
    builder.addCase(loginUser.fulfilled, (state, action) => {
      state.user = action.payload;
      state.sliceMoney = action.payload.money;
      // state.error = false;
    });
    builder.addCase(loginUser.rejected, (state, action) => {
      // state.error = true;
      //
    });

    builder.addCase(registerUser.fulfilled, (state, action) => {
      state.user = action.payload;
      // state.error = false;
    });

    builder.addCase(registerUser.rejected, (state, action) => {
      // state.error = true;
    });

    builder.addCase(updateUser.fulfilled, (state, action) => {
      state.user = action.payload;
      // state.error = false;
    });

    builder.addCase(depositMoney.fulfilled, (state, action) => {
      state.user = action.payload;
      state.sliceMoney = state.user?.money;
      console.log(state.sliceMoney);
    });

    builder.addCase(withdrawMoney.fulfilled, (state, action) => {
      state.user = action.payload;
      state.sliceMoney = state.user?.money;
      console.log(state.sliceMoney);
    });

    builder.addCase(retrieveUserScores.fulfilled, (state: any, action: any) => {
      let sortScores: IUser[] | any = new Array();
      let userBuffer: IUser | any;

      sortScores = action.payload;
      for (let i = 0; i < sortScores.length; i++) {
        for (let j = i + 1; j < sortScores.length; j++) {
          if (sortScores[i].money < sortScores[j].money) {
            userBuffer = sortScores[i];
            sortScores[i] = sortScores[j];
            sortScores[j] = userBuffer;
          }
        }
      }
      state.users = sortScores;
      console.log(state.users);
    });

    builder.addCase(sendMail.fulfilled, (state: any, action: any) => {
      //state.users = action.payload;
    });
  },
});
// If we had normal actions and reducers we would export them like this
// export const { toggleError } = UserSlice.actions;
export const { logoutUser, setSliceMoney, userBet, toggleLock } = UserSlice.actions;

export default UserSlice.reducer;
