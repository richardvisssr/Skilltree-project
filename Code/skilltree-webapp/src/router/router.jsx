import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import HomeScreen from "../screens/homeScreen";
import RegisterScreen from "../screens/registerScreen";

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
        path: "/register",
        element: <RegisterScreen />,
    },
];

const router = createBrowserRouter(routes);

export default router;
