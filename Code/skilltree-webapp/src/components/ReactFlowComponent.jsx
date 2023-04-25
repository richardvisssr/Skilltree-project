import React from "react";

import ReactFlow, { useNodesState, useEdgesState } from "reactflow";

import CustomNode from "./customNode";

import "reactflow/dist/style.css";
import "../styles/styles.css";

const defaultViewport = { x: 0, y: 0, zoom: 1.5 };

const initialNodes = [
    {
        id: "1", data: { label: "-" }, type: "custom", position: { x: 100, y: 100 },
    },
    {
        id: "2", data: { label: "Node 2" }, type: "custom", position: { x: 100, y: 200 },
    },
];

const nodeTypes = {
    custom: CustomNode,
};

function ReactFlowComponent() {
    // eslint-disable-next-line no-unused-vars
    const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
    // eslint-disable-next-line no-unused-vars
    const [edges, edgeChange, onEdgeChange] = useEdgesState([]);

    return (
        <div className="w-full flex-auto">
            <ReactFlow
                nodes={nodes}
                edges={edges}
                nodeTypes={nodeTypes}
                onNodesChange={onNodesChange}
                onEdgesChange={onEdgeChange}
                defaultViewport={defaultViewport}
                minZoom={0.2}
                maxZoom={4}
            />
        </div>
    );
}

export default ReactFlowComponent;
