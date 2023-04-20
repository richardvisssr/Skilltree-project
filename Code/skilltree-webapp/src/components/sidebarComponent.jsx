import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux'

import '../styles/styles.css';
import { fetchAllSkilltreesActionAsync, skilltreeSlice } from '../actions/SkilltreeAction'; 


export default function SidebarComponent() {

    
    const dispatch = useDispatch();

    function handleButtonClick() {
        dispatch(skilltreeSlice(true));
      }

    const skilltrees = useSelector((state) => state.skilltree.skilltrees)

    //Voor te testen, later moet er een reducer komen voor de users
    const [docentId, setDocentId] = useState(1);

    useEffect(() => {
        dispatch(fetchAllSkilltreesActionAsync(docentId))
      }, []);
    
    const skilltreeList = () => {
        const buttons = skilltrees.map((skilltree, index) =>
            <button key={index} className="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group">
                <span className="ml-4">{skilltree.title}</span>
            </button>)
        return (<li>
            {buttons}
        </li>)
    }

    return (
        <div>
            <aside id="separator-sidebar" className="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
                <div className="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
                    <ul className="space-y-2 font-medium">
                        <li>
                        <button className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700" onClick={handleButtonClick} >
                                <span className="ml-12">Skilltrees</span>
                                <span className="mb-1 ml-12 text-3xl">+</span>
                            </button>
                        </li>
                    </ul>
                    <ul className="pt-4 mt-4 space-y-2 font-medium border-t border-gray-200 dark:border-gray-700">
                        {skilltreeList()}
                    </ul>
                </div>
            </aside>
        </div>
    )
}