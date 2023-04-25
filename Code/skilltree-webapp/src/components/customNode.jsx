import React from "react";
import { Handle, Position } from "reactflow";

import "./nodeStyle.css";

export default function CustomNode({ isConnectable }) {
    const label = "Kaas";

    return (
        <div className="customNode">
            <div
                className="customNodeBody bg-amber-400"
            >
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
                {label}
            </div>
        </div>
    );
}
