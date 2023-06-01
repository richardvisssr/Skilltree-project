import React, { useEffect, useState } from "react";
import DocentView from "./users/docentView";
import StudentView from "./users/studentView";
import { Navigate } from 'react-router-dom';

export default function HomeScreen() {
  const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));

  const currentUserRoleId = currentUser.roleId;
  let view;

  if (currentUserRoleId !== null) {

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

    return (
      <div>
        {view}
      </div>
    );
  } else {
    return (
      <Navigate to="/login" />
    );
  }
}
