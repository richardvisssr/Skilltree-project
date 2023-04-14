import React, {useCallback, useEffect, useState} from 'react';
import ReactFlow, {useNodesState, useEdgesState, addEdge} from 'reactflow';
import 'reactflow/dist/style.css';

import './updatenode.css';

import CustomNode from './CustomNode';

const initialNodes = [

  { id: '1', type: 'customnode', position: { x: 100, y: 100 } },
  { id: '2', type: 'customnode', position: { x: 100, y: 200 } },
  { id: '3', type: 'customnode', position: { x: 200, y: 200 } },
  { id: '4', type: 'customnode', position: { x: 200, y: 100 } },
];

const initialEdges = [{ id: 'e1-2', source: '1', target: '2' }];
const defaultViewport = { x: 0, y: 0, zoom: 1.5 };

const UpdateNode = () => {
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  const nodeTypes = {
    customnode: CustomNode,
  };

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), [setEdges]);

  return (
      <div className="fullscreen">
        <h1>Update Node</h1>
        <ReactFlow
            nodes={nodes}
            edges={edges}
            nodeTypes={nodeTypes}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            defaultViewport={defaultViewport}
            onConnect={onConnect}
            minZoom={0.2}
            maxZoom={4}
            attributionPosition="bottom-left"
        >
        </ReactFlow>
      </div>
  );
};

export default UpdateNode;
