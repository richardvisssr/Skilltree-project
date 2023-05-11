import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AiFillCloseCircle } from "react-icons/ai";

import "../styles/styles.css";

export default function StudentCardComponent() {
    const dispatch = useDispatch();
    const userId = useSelector((state) => state.user.userId);
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const [showStudentCard, setShowStudentCard] = useState(false);

    const students = ["Jorian", "Richard", "Mitchel", "Thomas", "Sanad", "Kim", "Jeroen", "Jesse", "Jord", "Jordy", "Jordi", "Jordie"];

    // useEffect(() => {
    //     dispatch(fetchAllStudentsActionAsync(userId, currentSkilltree.id));
    // }, []);

    const studentList = () => {
        try {
            const studentsList = students.map((student) => (
                <div
                key={student}
                className="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg dark:text-white group"
            >
                    <span className="ml-12">
                        { student }
                    </span>
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
<aside id="default-sidebar" className="fixed top-19 right-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
   <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-pink-700">
      <ul className="space-y-2 font-medium">
         {studentList()}
      </ul>
   </div>
</aside>
        );
}