import React, { useState } from "react";
import { useDispatch } from "react-redux";

import "../../styles/styles.css";

export function EditNodeComponent() {
    const dispatch = useDispatch();

    const [editCardShowState, setEditCardShowState] = useState(true);
    
    const showCard = () => {
        setEditCardShowState(true);
        console.log("Hello");
    };

    const hideCard = () => {
        setEditCardShowState(false);
    };
    /* eslint-disable react/jsx-wrap-multilines */
    /* eslint-disable operator-linebreak */
    return (
        <div>
            {editCardShowState ?
                <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                    <div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all w-5/6">
                        <div className="bg-white px-4 pb-4 pt-5">
                            <p className="text-center">Gebruik een komma om een nieuwe BeoordelingsCriteria toe te voegen</p>
                            <div className="mt-6 flex items-center justify-center gap-x-6">
                                <button type="button" className="text-m font-semibold leading-6 text-gray-900" onClick={hideCard}>Annuleren</button>
                                <button
                                    type="submit"
                                    className="rounded-md bg-indigo-600 px-3 py-2 text-m font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                >
                                    Opslaan
                                </button>
                            </div>
                        </div>
                    </div>
                </div> : null}
        </div>
    );
}
