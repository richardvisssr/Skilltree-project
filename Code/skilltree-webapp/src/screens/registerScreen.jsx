import React from "react";
import { Navigate } from 'react-router-dom';

import RegisterAccountComponent from "../components/register/RegisterAccountComponent"

export default function RegisterScreen() {
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    const DOCENT_ROLE = 1;
    const STUDENT_ROLE = 2;

    if (currentUser !== null) {
        if (currentUser.roleId === DOCENT_ROLE) {
            return (
                <RegisterAccountComponent />
            );
        } else if (currentUser.roleId === STUDENT_ROLE) {
            return <Navigate to="/home" />;
        }
    } else {
        return <Navigate to="/login" />;
    }
}
