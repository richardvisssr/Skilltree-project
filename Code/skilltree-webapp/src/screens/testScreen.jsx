import React from "react";
import StudentCardComponent from "../components/StudentCardComponent";

export default function HomeScreen() {

    return (
        <div className="flex-row flex w-screen">
            <StudentCardComponent />
        </div>
    );
}