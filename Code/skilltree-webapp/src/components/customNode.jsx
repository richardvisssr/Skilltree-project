import React from "react";
import { useDispatch } from "react-redux";
import { Handle, Position } from "reactflow";
import { AiFillEdit } from "react-icons/ai";

import { showCreateCard } from "../actions/Node";
import "./nodeStyle.css";

export default function CustomNode({ isConnectable, data }) {
    const label = data.label;

    const dispatch = useDispatch();

    const handleButton = () => {
        dispatch(showCreateCard());
    };

    return (
        <div className="customNode">
            <div className="customNodeBody bg-slate-200">
                <div className="edit-button">
                    <button 
                        type="button" 
                        onClick={handleButton}
                    >
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
