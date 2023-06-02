import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {useNavigate} from "react-router-dom";
import {AiOutlineLogout} from "react-icons/ai";

import "../../../styles/styles.css";
import {
    fetchAllSkilltreesActionAsync,
    setCurrentSkilltreeAction,
} from "../../../actions/SkilltreeAction";
import { showCreateCard } from "../../../actions/NodeAction";

export default function SidebarComponent() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const skilltrees = useSelector((state) => state.skilltree.skilltrees);
    const showNodeCard = useSelector((state) => state.node.showCard);
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    const userId = currentUser.id;
    const roleId = currentUser.roleId;

    function handleButtonClick(id) {
        let currentSkilltree;
        skilltrees.map((skilltree) => {
            if (skilltree.id === id) {
                currentSkilltree = skilltree;
            }
        });

        if (showNodeCard) {
            dispatch(showCreateCard());
        }
        dispatch(setCurrentSkilltreeAction(currentSkilltree));
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
                    className="flex items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group"
                    onClick={() => handleButtonClick(skilltree.id)}
                >
                    <span className="ml-12">
                        { skilltree.title }
                    </span>
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
            <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
                <ul className="space-y-2 font-medium">
                    <li>
                        <div className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white"
                        >
                            <span className="mt-2 mb-2 ml-12">Skilltrees</span>
                        </div>
                    </li>
                </ul>
                <ul className="pt-4 mt-4 space-y-2 font-medium border-t border-gray-200 dark:border-gray-700">
                    {skilltreeList()}
                </ul>
                <div className="absolute bottom-4 bg-gray-50 dark:bg-gray-800 border-t border-gray-200 dark:border-gray-700 w-44">
                    <ul className="mt-auto space-y-2 font-medium ">
                        <button
                            type="button"
                            className="flex z-50 items-center w-full p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group"
                            onClick={() => logout()}
                        >
                            <div className="flex" >
                                <AiOutlineLogout size={20} />
                                <span className="ml-6">Log uit</span>
                            </div>
                        </button>
                    </ul>
                </div>
            </div>
        </aside>
    );
}
