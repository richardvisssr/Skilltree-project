import React, { useState } from "react";
import { useDispatch } from "react-redux";

import "../../styles/styles.css";

export function EditNodeComponent() {
    const dispatch = useDispatch();

    const [editCardShowState, setEditCardShowState] = useState(true);
    

    const hideCard = () => {
        setEditCardShowState(false);
    };
    /* eslint-disable react/jsx-wrap-multilines */
    /* eslint-disable operator-linebreak */
    return (
        <div>
            {editCardShowState ?
            
            <div>

            </div>

            : null}
        </div>
    );
}
