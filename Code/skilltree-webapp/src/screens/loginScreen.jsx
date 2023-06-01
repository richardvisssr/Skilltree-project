import React from "react";
import { Navigate } from 'react-router-dom';

import LoginComponent from "../components/LoginComponent";

export default function LoginScreen() {
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    
    if (currentUser === null) {
        return (
            <LoginComponent />
        );
    } else {
        return <Navigate to="/home" />;
    }
}
