import React, {useCallback, useEffect, useState} from 'react';
import ReactFlow, {useNodesState, useEdgesState, addEdge} from 'reactflow';
import 'reactflow/dist/style.css';

import './updatenode.css';

const initialNodes = [

  { id: '1', position: { x: 100, y: 100 } },
  { id: '2', position: { x: 100, y: 200 } },
  { id: '3', position: { x: 200, y: 200 } },
  { id: '4', position: { x: 200, y: 100 } },
];

const initialEdges = [{ id: 'e1-2', source: '1', target: '2' }];
const defaultViewport = { x: 0, y: 0, zoom: 1.5 };

const UpdateNode = () => {
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), [setEdges]);
  const addNode = (node) => {
    setNodes((nds) => nds.concat(node));
  }

  const getNextNodeId = () => {
    const lastNode = nodes[nodes.length - 1];
    return lastNode.id + 1;
  }

  return (
      <div className="fullscreen">
        <h1>Update Node</h1>
        <button onClick={() => addNode({
          id: getNextNodeId(),
          position: { x:75, y:75 },
          data: { label: `Node` },
        })}>Update Node</button>
        <ReactFlow
            nodes={nodes}
            edges={edges}
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
