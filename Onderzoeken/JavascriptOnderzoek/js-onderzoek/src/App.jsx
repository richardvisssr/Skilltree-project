import React, {useCallback, useRef, useState} from 'react';
import ReactFlow, {useNodesState, useEdgesState, addEdge, useReactFlow, ReactFlowProvider} from 'reactflow';
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
  const {project} = useReactFlow();
  const reactFlowWrapper = useRef(null)
  const [placeNode, setPlaceNode] = useState(false);
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), [setEdges]);
  const addNode = (node) => {
    setNodes((nds) => nds.concat(node));
  }

  const getNextNodeId = () => {
    const lastNode = nodes[nodes.length - 1];
    return (parseInt(lastNode.id) + 1).toString();
  }

  const makeNodeOnMouseClick = useCallback((event) => {
          const targetIsPane = event.target.classList.contains('react-flow__pane');

          if (targetIsPane && placeNode) {
            // we need to remove the wrapper bounds, in order to get the correct position
            const {top, left} = reactFlowWrapper.current.getBoundingClientRect();
            const id = getNextNodeId();
            const newNode = {
              id,
              // we are removing the half of the node width (75) to center the new node
              position: project({x: event.clientX - left - 110, y: event.clientY - top - 110}),
              data: {label: `Node ${id}`},
            };
            addNode(newNode);
            setPlaceNode(false);
          }
  });

  return (
      <div className="fullscreen" ref={reactFlowWrapper}>
        <h1>Update Node</h1>
        <button onClick={() => setPlaceNode(!placeNode)}>Place Node</button>
        <ReactFlow
            onPaneClick={makeNodeOnMouseClick}
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

export default () => (
    <ReactFlowProvider>
      <UpdateNode />
    </ReactFlowProvider>
);
