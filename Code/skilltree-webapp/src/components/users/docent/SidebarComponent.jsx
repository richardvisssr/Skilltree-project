import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import "../../../styles/styles.css";
import "./deleteButton.css";
import {
    fetchAllSkilltreesActionAsync,
    addSkiltreeTopbar,
    setCurrentSkilltreeAction,
    showDeleteCard,
} from "../../../actions/SkilltreeAction";
import { AiFillDelete } from "react-icons/ai";

export default function SidebarComponent() {
    const dispatch = useDispatch();
    const skilltrees = useSelector((state) => state.skilltree.skilltrees);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    // Voor te testen, later moet er een reducer komen voor de users
    const userId = useSelector((state) => state.user.userId);

    function handleButtonClick(id) {
        let currentSkilltree;
        skilltrees.map((skilltree) => {
            if (skilltree.id === id) {
                currentSkilltree = skilltree;
            }
        });
        dispatch(setCurrentSkilltreeAction(currentSkilltree));
    }

    function deleteSkilltree(id) {
        dispatch(setCurrentSkilltreeAction(id));
        dispatch(showDeleteCard(id));
    }

    function handleNewButtonClick() {
        dispatch(addSkiltreeTopbar());
    }

    useEffect(() => {
        dispatch(fetchAllSkilltreesActionAsync(userId));
    }, []);

    const skilltreeList = () => {
        try {
            const buttons = skilltrees.map((skilltree) => (
                    <button
                        type="button"
                        key={skilltree.id}s
                        className="skilltree-button flex justify-between items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group"
                        onClick={() => handleButtonClick(skilltree.id)}
                    >

                        <div className="ml-12">
                            { skilltree.title }
                        </div>
                        <div
                            className="skilltree-delete"
                            onClick={() => deleteSkilltree(skilltree.id)}>
                            <AiFillDelete/>
                        </div>
                    </button>
            ));
            return (
                <li>
                    {buttons}
                </li>
            );
        } catch {
            return "geen skilltrees gevonden";
        }
    };

    const newSkilltreeButton = () => {
        if (newSkilltree) {
            return "-";
        }
        return "+";
    };

    return (
        <aside
            id="separator-sidebar"
            className="top-0 left-0 z-40 w-64 h-screen"
            aria-label="Sidebar"
        >
            <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
                <ul className="space-y-2 font-medium">
                    <li>
                        <button
                            onClick={() => handleNewButtonClick()}
                            type="button"
                            className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                        >
                            <span className="ml-12">Skilltrees</span>
                            <span className="mb-1 ml-12 text-3xl">{ newSkilltreeButton() }</span>
                        </button>
                    </li>
                </ul>
                <ul className="pt-4 mt-4 space-y-2 font-medium border-t border-gray-200 dark:border-gray-700">
                    {skilltreeList()}
                </ul>
            </div>
        </aside>
    );
}
