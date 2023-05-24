import React, { useState } from "react";
import DocentView from "../screens/docentView";
import StudentView from "../screens/studentView";

export default function HomeScreen() {
  const showDocentView = true;

  return (
    <div>
      {/* Hier komt de inhoud van de HomeScreen */}
      {showDocentView && <StudentView />}
      {/*{showDocentView && <StudentView />}*/}
    </div>
  );
}
