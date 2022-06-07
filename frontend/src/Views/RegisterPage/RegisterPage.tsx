import React, { useEffect } from 'react';
import './RegisterPage.css';

import { RegisterForm } from '../../Components/RegisterForm/RegisterForm';

// will go inside App tsx
export const RegisterPage: React.FC = () => {
  return (
    <div className="loginPage">
      <RegisterForm />
    </div>
  );
};
