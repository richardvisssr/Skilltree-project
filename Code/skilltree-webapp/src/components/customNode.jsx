import React from "react";
import { useDispatch } from "react-redux";
import { Handle, Position } from "reactflow";
import { AiFillEdit } from "react-icons/ai";

import { showCreateCard } from "../actions/Node";
import "./nodeStyle.css";

export default function CustomNode({ isConnectable }) {
    const label = "Kaas";

    const dispatch = useDispatch();
    // const [isEditNodeComponentVisible, setIsEditNodeComponentVisible] = useState(false);

    const handleButton = () => {
        dispatch(showCreateCard());
    };


    return (
        <div className="customNode">
            <div
                className="customNodeBody bg-amber-300"
            >
                <div className="edit-button">
                    <button type="button" onClick={handleButton}>
                        <AiFillEdit />
                    </button>
                </div>
                <Handle
                    className="targetHandle"
                    style={{ zIndex: 2 }}
                    position={Position.Right}
                    type="source"
                    isConnectable={isConnectable}
                />
                <Handle
                    className="targetHandle"
                    position={Position.Left}
                    type="target"
                    isConnectable={isConnectable}
                />
                <div className="flex justify-center items-center w-full">
                    {label}
                </div>
            </div>
        </div>
    );
}
