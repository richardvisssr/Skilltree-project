import React, { useState } from "react";
import { AiFillDelete, AiFillEdit } from "react-icons/ai";
import { DeleteNodeComponent } from "./DeleteNodeComponent";
import { useDispatch } from "react-redux";
import {Handle, Position, useStore} from "reactflow";

import { showCreateCard, currentNodeSelectedAction } from "../../../../actions/NodeAction";
import "../nodeStyle.css";
import { useState } from "react";

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
            <div className="customNode-docent">
                <div className="customNodeBody bg-slate-200">
                    <div className="flex">
                        <div className="node-button-container">
                            <button
                                className="node-button edit-button"
                                type="button" 
                                onClick={handleButton}
                            >
                                <AiFillEdit />
                            </button>
                        </div>
                        <div className="node-button-container">
                            <button
                                className="node-button delete-button"
                                type="button"
                                onClick={showDeleteNodeComponent}
                            >
                            <AiFillDelete />
                            </button>
                        </div>
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

            : null }

            {isDeleteNodeComponentVisible && <DeleteNodeComponent nodeId={nodeId} showDeleteNodeComponent={showDeleteNodeComponent} hideNode={hideNode} />}
        </div>
    );
}
