import React from 'react';
import { createBrowserRouter, Navigate } from 'react-router-dom';

import TestScreen from '../screens/testScreen';
import HomeScreen from '../screens/homeScreen';

const routes = [
  {
    path: '/',
    element: <Navigate to="/home" replace />
  },
  {
    path: '/test',
    element: <TestScreen />
  },
  {
    path: '/home',
    element: <HomeScreen />
  }
];

const router = createBrowserRouter(routes);

export default router;
