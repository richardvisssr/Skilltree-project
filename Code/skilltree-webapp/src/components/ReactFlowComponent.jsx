import React, { useState, useRef, useCallback, useEffect }  from "react";
import { useDispatch, useSelector } from "react-redux";
import ReactFlow, {
  ReactFlowProvider,
  addEdge,
  useNodesState,
  useEdgesState,
  Controls,
} from 'reactflow';
import CustomNode from "./customNode";

import { fetchAllNodesFromSkilltree } from "../actions/SkilltreeAction";
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
    // const [allNodes, setAllNodes] = useState([]);

    // const skilltreeId = useSelector((state) => state.skilltree.currentSkilltree.id);

    const dispatch = useDispatch();

    const allFetchedNodes = useSelector((state) => state.skilltree.nodes);    
    
    const convertFetchToNodes = () => {
      let tempArray = [];
      allFetchedNodes.map((node) => {
        const tempObj = {
          id: `${node.id}`,
          type: 'custom',
          data: { label: `${node.skill}` },
          position: { x: node.positionX, y: node.positionY },
        }
        tempArray.push(tempObj);
      })
      setNodes(tempArray);
    }

    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    let skilltreeId;
    if (skilltree !== null) {
      skilltreeId = skilltree.id;
    }

    useEffect(() => {
      dispatch(fetchAllNodesFromSkilltree(skilltreeId));
    }, [skilltreeId]);

    useEffect(() => {
      convertFetchToNodes();
    }, [allFetchedNodes])


  let id = 0;
  const getId = () => `${id++}`;

  const reactFlowWrapper = useRef(null);
  const [reactFlowInstance, setReactFlowInstance] = useState(null);
  const [nodes, setNodes, onNodesChange] = useNodesState([]);
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




