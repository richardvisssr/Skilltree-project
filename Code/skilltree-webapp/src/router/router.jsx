import React from "react";
import { createBrowserRouter, Navigate } from "react-router-dom";

import TestScreen from "../screens/testScreen";
import CreateNodeScreen from "../screens/CreateNodeScreen";

const routes = [
    {
        path: "/",
        element: <Navigate to="/test" replace />,
    },
    {
        path: "/test",
        element: <TestScreen />,
    },
    {
        path: "/createNode",
        element: <CreateNodeScreen />,
    },
];

const router = createBrowserRouter(routes);

export default router;
