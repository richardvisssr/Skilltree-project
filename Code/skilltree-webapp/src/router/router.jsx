import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import HomeScreen from "../screens/homeScreen";
import TestScreen from "../screens/testScreen";

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
        path: "/test",
        element: <TestScreen />,
    },
];

const router = createBrowserRouter(routes);

export default router;
