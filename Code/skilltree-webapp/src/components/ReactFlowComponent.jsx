import React, { useState, useRef, useCallback }  from "react";
import ReactFlow, {
  ReactFlowProvider,
  addEdge,
  useNodesState,
  useEdgesState,
  Controls,
} from 'reactflow';
import { useSelector } from "react-redux";
import CustomNode from "./customNode";

import "reactflow/dist/style.css";
import "../styles/styles.css";

const defaultViewport = { x: 0, y: 0, zoom: 1.5 };

const initialNodes = [
  {
    id: '1',
    type: 'custom',
    data: { label: 'nieuwe node' },
    position: { x: 250, y: 5 },
  },
];

const nodeTypes = {
    custom: CustomNode,
};

function ReactFlowComponent() {
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    let skilltreeId = null;
    if (skilltree !== null) {
      skilltreeId = skilltree.id;
    }


  let id = 0;
  const getId = () => `${id++}`;

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
        data: { label: `Nieuwe node` },
        skilltreeId
      };
  
      setNodes((prevNodes) => [...prevNodes, newNode]);
    },
    [reactFlowInstance]
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




