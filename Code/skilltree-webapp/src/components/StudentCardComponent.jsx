import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchAllStudentsActionAsync } from "../actions/StudentAction";

import "../styles/styles.css";

export default function StudentCardComponent() {

    const dispatch = useDispatch();
    const students = useSelector((state) => state.student.students);

    useEffect(() => {
        dispatch(fetchAllStudentsActionAsync());
    }, []);
    
    const studentList = () => {
        try {
            const studentsList = students.map((student) => (
                <div
                key={student}
                className="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg dark:text-white group"
            >
                
            <div className="flex items-center mb-4">
                <input id="default-checkbox" type="checkbox" value="" className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
                <label for="default-checkbox" className="ml-2 text-ml font-medium text-gray-900 dark:text-gray-300">{ student.firstname + " " + student.lastname }</label>
            </div>
                </div>
            ));
            return (
                <li>
                    {studentsList}
                </li>
            );
        } catch {
            return "geen studenten gevonden";
        }
    };

    return (
<aside id="default-sidebar" className="absolute top-16 right-0 z-40 w-64 h-[calc(100vh-64px)] transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
   <div className="h-[calc(100vh-64px)] px-3 py-4 overflow-y-auto bg-pink-700 dark:bg-pink-700">
      <ul className="space-y-2 font-medium">
         {studentList()}
      </ul>
   </div>
</aside>
        );
}