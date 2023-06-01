import React from "react";
import { Navigate } from 'react-router-dom';

import DocentView from "./users/docentView";
import StudentView from "./users/studentView";

export default function HomeScreen() {
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    const currentUserRoleId = currentUser.roleId;
    let view;

    if (currentUser !== null) {
        const currentUserRoleId = currentUser.roleId;
        switch (currentUserRoleId) {
            case 1:
                view = <DocentView />;
                break;
            case 2:
                view = <StudentView />;
                break;
            default:
                view = 'Onbekende userID';
                break;
        }
    }
}
