import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import "../styles/styles.css";

export default function StudentCardComponent() {

    const [selectedStudents, setSelectedStudents] = useState([]);

    const students = [
        {ID: 1, Firstname: "Jorian", Lastname: "henkie"}, 
        {ID: 2, Firstname: "piet", Lastname: "Jansen"}, 
        {ID: 3, name: "henk", Lastname: "Peters"}
    ];

    // adds en removes students from selectedStudents list
    const handleChange = (event) => {
        if (event.target.checked) {
            selectedStudents.push(event.target.value)
        } else {
            var filteredArray = selectedStudents.filter(e => e !== event.target.value)
            setSelectedStudents(filteredArray)
        }
    }

    const studentList = () => {
        const studentsList = students.map((student) => (
            <div
                key={student.ID}
                className="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg dark:text-white group"
            >
                
                <div className="flex items-center mb-4">
                    <input 
                        id="default-checkbox" 
                        type="checkbox"
                        value={student.ID} 
                        onChange={handleChange} 
                        className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                    />
                    <label 
                        htmlFor="default-checkbox" 
                        className="ml-2 text-ml font-medium text-gray-900 dark:text-gray-300"
                    >
                        { student.Firstname + " " + student.Lastname }
                    </label>
                </div>
            </div>
        ));
        return (
            <li>
                {studentsList}
            </li>
        );
    };

    return (
        <aside id="default-sidebar" className="fixed top-0 right-0 z-40 w-64 transition-transform h-screen -translate-x-full sm:translate-x-0" aria-label="Sidebar">
            <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-pink-700">
                <ul className="space-y-2 font-medium">
                    {studentList()}
                </ul>
            </div>
        </aside>
    );
}