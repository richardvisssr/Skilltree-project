import React from "react";
import { useDispatch, useSelector } from "react-redux";
import {Handle, Position, useStore} from "reactflow";
import { AiFillEdit } from "react-icons/ai";

import { showCreateCard, currentNodeSelectedAction } from "../actions/NodeAction";
import "./nodeStyle.css";

const connectionNodeIdSelector = (state) => state.connectionNodeId;
export default function CustomNode({ isConnectable, data }) {
    const label = data.label;
    const nodeId = data.nodeId;
    //test
    const assesmentCriteria = useSelector((state) => state.node.assesmentCriteria)


    const dispatch = useDispatch();
    const connectionNodeId = useStore(connectionNodeIdSelector);
    const isTarget = connectionNodeId && connectionNodeId !== data.id;
    const targetHandleStyle = { zIndex: isTarget ? 3 : 1 };

    const handleButton = () => {
        dispatch(currentNodeSelectedAction(nodeId));
        dispatch(showCreateCard());
        // console.log(assesmentCriteria + "Opgehaald");
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
                    style={targetHandleStyle}
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
