import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import HomeScreen from "../screens/homeScreen";
import LoginScreen from "../screens/loginScreen";

const routes = [
    {
        path: "/",
        element: <Navigate to="/home" replace />,
    },
    {
        path: "/home",
        element: <HomeScreen />,
    },
    {
        path: "/login",
        element: <LoginScreen />,
    }
];

const router = createBrowserRouter(routes);

export default router;
