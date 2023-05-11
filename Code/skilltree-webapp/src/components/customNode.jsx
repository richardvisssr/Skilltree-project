import React from "react";
import { Handle, Position } from "reactflow";
import { AiFillDelete, AiFillEdit } from "react-icons/ai";
import { DeleteNodeComponent } from "./deleteNode/DeleteNodeComponent";
import { useState } from "react";

import "./nodeStyle.css";

export default function CustomNode({ isConnectable }) {
    const label = "Kaas";
    const nodeId = -1

    const [isDeleteNodeComponentVisible, setIsDeleteNodeComponentVisible] = useState(false);

    const showDeleteNodeComponent = () => {
        setIsDeleteNodeComponentVisible(!isDeleteNodeComponentVisible);
    };

    const [isNodeVisible, setIsNodeVisible] = useState(true);

    const hideNode = () => {
        setIsNodeVisible(false);
    }

    return (
        <div>
            {isNodeVisible ?

            <div>
                <div className="customNode">
                    <div className="customNodeBody bg-amber-300">
                        <div className="flex justify-between">
                            <div className="node-button-container">
                                <button
                                    className="node-button edit-button"
                                    type="button"
                                >
                                <AiFillEdit/>
                                </button>
                            </div>
                            <div className="node-button-container">
                                <button
                                    className="node-button delete-button"
                                    type="button"
                                    onClick={showDeleteNodeComponent}
                                >
                                <AiFillDelete/>
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
                            position={Position.Left}
                            type="target"
                            isConnectable={isConnectable}
                        />
                        <div className="flex justify-center items-center w-full">
                            {label}
                        </div>
                    </div>
                </div>
            </div>
            : null}

            {isDeleteNodeComponentVisible && <DeleteNodeComponent nodeId={nodeId} showDeleteNodeComponent={showDeleteNodeComponent} hideNode={hideNode} />}
        
        </div>
    );
}
