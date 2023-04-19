import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import TestScreen from "../screens/testScreen";

const routes = [
    {
        path: "/",
        element: <Navigate to="/test" replace />,
    },
    {
        path: "/test",
        element: <TestScreen />,
    },
];

const router = createBrowserRouter(routes);

export default router;
