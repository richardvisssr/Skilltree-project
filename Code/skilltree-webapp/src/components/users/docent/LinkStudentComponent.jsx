import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import {
    fetchAllStudentsActionAsync,
    fetchAllStudentsFromSkilltreeActionAsync,
    setSelectedStudentsAction
} from "../../../actions/StudentAction";
import "../../../styles/styles.css";

export default function LinkStudentComponent() {
    const dispatch = useDispatch();
    const selectedStudentsFromStore = useSelector((state) => state.student.selectedStudents);
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);

    const [students, setStudents] = useState([]);

    useEffect(() => {
        const fetchStudents = async () => {
            const kaas = await dispatch(fetchAllStudentsActionAsync());
            setStudents(kaas)
        };

        const fetchSelectedStudents = async () => {
            const result = await dispatch(fetchAllStudentsFromSkilltreeActionAsync(skilltree.id));
            if (result) {
                dispatch(setSelectedStudentsAction(result))
            }
        }

        fetchStudents();
        fetchSelectedStudents();
    }, [skilltree]);

    // adds en removes students from selectedStudents list
    const handleChange = (event) => {
        if (event.target.checked) {
            const student = students.filter(student => parseInt(student.id) === parseInt(event.target.value));
            const tempArr = selectedStudentsFromStore;
            tempArr.push(student[0]);
            dispatch(setSelectedStudentsAction(tempArr))
        } else {
            const filteredArray = selectedStudentsFromStore.filter(student => parseInt(student.id) !== parseInt(event.target.value));
            dispatch(setSelectedStudentsAction(filteredArray))
        }
    }

    const isChecked  = (studentId) => {
        for (let i = 0; i < selectedStudentsFromStore.length; i++) {
            if (parseInt(selectedStudentsFromStore[i].id) === parseInt(studentId)) {
                return true;
            }
        }
        return false;
    }

    const studentList = () => {
        if(students.length > 0) {
            const studentsList = students.map((student) => (
                <div
                    key={student.id}
                    className="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg dark:text-white group"
                >
                    <div className="flex items-center mb-4">
                        <input
                            id="default-checkbox"
                            type="checkbox"
                            value={student.id}
                            onChange={handleChange}
                            defaultChecked={isChecked(student.id)}
                            className="
                            w-4
                            h-4
                            text-blue-600
                            bg-gray-100
                            border-gray-300
                            rounded
                            focus:ring-blue-500
                            dark:focus:ring-blue-600
                            dark:ring-offset-gray-800
                            focus:ring-2
                            dark:bg-gray-700
                            dark:border-gray-600"
                        />
                        <label
                            htmlFor="default-checkbox"
                            className="ml-2 text-ml font-medium text-white dark:text-white"
                        >
                            {`${student.firstname} ${student.lastname}`}
                        </label>
                    </div>
                </div>
            ));
            return (
                <li>
                    {studentsList}
                </li>
            );
        }
        return null;
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
