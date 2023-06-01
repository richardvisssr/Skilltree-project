import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { MdPersonAddAlt1 } from "react-icons/md";
import { useNavigate } from "react-router-dom";

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
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const skilltrees = useSelector((state) => state.skilltree.skilltrees);
    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    // Voor te testen, later moet er een reducer komen voor de users
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    const userId = currentUser.id;
    const roleId = currentUser.roleId;
    console.log("AAAAAA", currentUser)

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
        dispatch(fetchAllSkilltreesActionAsync(userId, roleId));
    }, []);

    const skilltreeList = () => {
        try {
            const buttons = skilltrees.map((skilltree) => (
                    <button
                        type="button"
                        key={skilltree.id}
                        className="
                            skilltree-button
                            flex justify-between
                            items-center
                            w-full p-2
                            text-gray-900
                            transition duration-75
                            rounded-lg
                            hover:bg-gray-100
                            dark:hover:bg-gray-700
                            dark:text-white group"
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

    const handleRegisterButtonClick = () => {
        navigate("/registreer");
    }

    const registerButton = () => {
        return(
        <button
            type="button"
            className="flex z-50 items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group"
            onClick={() => handleRegisterButtonClick()}
        >
            <div className="flex" >
                <MdPersonAddAlt1 size={20} /> 
                <span className="ml-4"> &nbsp; Registreer</span>
            </div>  
        </button>
        )

    };

    const newSkilltreeButton = () => {
        if (newSkilltree) {
            return "-";
        }
        return "+";
    };

    function logout() {
        sessionStorage.clear();
        navigate("/login");
    }

    return (
        <aside
        id="separator-sidebar"
        className="top-0 left-0 z-40 w-64 h-screen"
        aria-label="Sidebar"
        >
            <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800 flex flex-col">
                <ul className="space-y-2 font-medium">
                    <li>
                        <button
                            onClick={() => handleNewButtonClick()}
                            type="button"
                            className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                        >
                            <span className="ml-4">Skilltrees</span>
                            <span className="mb-1 ml-12 text-3xl">{newSkilltreeButton()}</span>
                        </button>
                    </li>
                </ul>
                <ul className="pt-4 mt-4 mb-20 space-y-2 font-medium border-t border-gray-200 dark:border-gray-700">
                    {skilltreeList()}
                </ul>
                <div className="absolute bottom-4 bg-gray-50 dark:bg-gray-800 border-t border-gray-200 dark:border-gray-700 w-44">
                    <ul className="mt-auto space-y-2 font-medium ">
                        {registerButton()}
                    </ul>
                    <ul className="mt-auto space-y-2 font-medium ">
                        <button
                            className="flex font-medium justify-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700"
                            type="button"
                            onClick={() => logout()}
                        >
                            <span className="text-center">log uit</span>
                        </button>
                    </ul>
                </div>
            </div>
        </aside>

    );
}
