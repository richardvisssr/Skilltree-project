import React from "react";
import { useDispatch } from "react-redux";
import {Handle, Position, useStore} from "reactflow";

import { showCreateCard, currentNodeSelectedAction } from "../../../../actions/NodeAction";
import "../nodeStyle.css";

const connectionNodeIdSelector = (state) => state.connectionNodeId;
export default function CustomNode({ isConnectable, data }) {
    const label = data.label;
    const nodeId = data.nodeId;

    //test


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

        </div>
    );
    
}

