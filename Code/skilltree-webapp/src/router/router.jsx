import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import HomeScreen from '../screens/homeScreen';

const routes = [
  {
    path: '/',
    element: <Navigate to="/home" replace />
  },
  {
    path: '/home',
    element: <HomeScreen />
  }
];

const router = createBrowserRouter(routes);

export default router;
