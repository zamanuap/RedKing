import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

import App from './App';

import { persistStore } from 'redux-persist';
import { PersistGate } from 'redux-persist/integration/react';

import { Provider } from 'react-redux';
import { Store } from './Store';

// let persistor = persistStore(Store);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

root.render(
  <Provider store={Store}>
    {/* <PersistGate persistor={persistor}> */}
      <App />
    {/* </PersistGate> */}
  </Provider>
);
