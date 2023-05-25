import React from "react";
import { AiOutlineInfoCircle } from "react-icons/ai";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {Handle, Position, useStore} from "reactflow";

import { showCreateCard, currentNodeSelectedAction } from "../../../../actions/NodeAction";
import "./nodeStyle.css";

const connectionNodeIdSelector = (state) => state.connectionNodeId;
export default function CustomNode({ isConnectable, data }) {
    const label = data.label;
    const nodeId = data.nodeId;

    const [isDeleteNodeComponentVisible, setIsDeleteNodeComponentVisible] = useState(false);

    const showDeleteNodeComponent = () => {
        setIsDeleteNodeComponentVisible(!isDeleteNodeComponentVisible);
    };

    const [isNodeVisible, setIsNodeVisible] = useState(true);

    const hideNode = () => {
        setIsNodeVisible(false);
    }
    //test
    const assessmentCriteria = useSelector((state) => state.node.assessmentCriteria)


    const dispatch = useDispatch();
    const connectionNodeId = useStore(connectionNodeIdSelector);
    const isTarget = connectionNodeId && connectionNodeId !== data.id;
    const targetHandleStyle = { zIndex: isTarget ? 3 : 1 };

    const handleButton = () => {
        dispatch(currentNodeSelectedAction(nodeId));
        dispatch(showCreateCard());
    };

    return (
        <div>

            {isNodeVisible ?
            
            <div className="customNode">
                <div className="customNodeBody bg-slate-200">
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
                    <button
                        className="h-full"
                        type="button"
                        onClick={handleButton}>
                        <div className="flex justify-center items-center w-full">
                            {label}
                        </div>
                    </button>
                </div>
            </div>

            : null }

            {/* <div className="flex">
                    <div className="node-button-container">
                        <button
                            className="node-button edit-button"
                            type="button" 
                            onClick={handleButton}
                        >
                            <AiOutlineInfoCircle />
                        </button>
                    </div>
                </div> */}
        </div>
    );
    
}

