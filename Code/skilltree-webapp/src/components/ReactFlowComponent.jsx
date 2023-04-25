import React, { useState, useRef, useCallback }  from "react";
import ReactFlow, {
  ReactFlowProvider,
  addEdge,
  useNodesState,
  useEdgesState,
  Controls,
} from 'reactflow';

import CustomNode from "./customNode";

import "reactflow/dist/style.css";
import "../styles/styles.css";

const defaultViewport = { x: 0, y: 0, zoom: 1.5 };

const initialNodes = [
    {
        id: "0", data: { label: "-" }, type: "custom", position: { x: 100, y: 100 },
    },
    {
        id: "1", data: { label: "Node 2" }, type: "custom", position: { x: 100, y: 200 },
    },
];

const nodeTypes = {
    custom: CustomNode,
};

function ReactFlowComponent() {

  let id = 0;
  const getId = () => `dndnode_${id++}`;

  const reactFlowWrapper = useRef(null);
  const [reactFlowInstance, setReactFlowInstance] = useState(null);
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), []);

  const onDragOver = useCallback((event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = 'move';
  }, []);

  const onDrop = useCallback(
    (event) => {
      event.preventDefault();

      const reactFlowBounds = reactFlowWrapper.current.getBoundingClientRect();
      const type = event.dataTransfer.getData('application/reactflow');

      // check if the dropped element is valid
      if (typeof type === 'undefined' || !type) {
        return;
      }

      const position = reactFlowInstance.project({
        x: event.clientX - reactFlowBounds.left,
        y: event.clientY - reactFlowBounds.top,
      });
      const newNode = {
        id: getId(),
        type,
        position,
        data: { label: `${type} node` },
      };

      setNodes((nds) => nds.concat(newNode));
    },
    [getId, reactFlowInstance, setNodes]
  );

    return (
      <ReactFlowProvider>
        <div className="w-full flex-auto" ref={reactFlowWrapper}>
          <ReactFlow
            nodes={nodes}
            edges={edges}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            onConnect={onConnect}
            nodeTypes={nodeTypes}
            onInit={setReactFlowInstance}
            onDrop={onDrop}
            onDragOver={onDragOver}
            defaultViewport={defaultViewport}
            minZoom={0.2}
            maxZoom={4}
          >
            <Controls />
          </ReactFlow>
        </div>
      </ReactFlowProvider>
    );
}
 export default ReactFlowComponent;




