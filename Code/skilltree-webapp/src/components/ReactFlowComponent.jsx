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
import { fetchCreateNodeActionAsync, fetchHighestNodeIdActionAsync } from "../actions/NodeAction";
import "reactflow/dist/style.css";
import "../styles/styles.css";

const nodeTypes = {
    custom: CustomNode,
};

function ReactFlowComponent() {


    const dispatch = useDispatch();

    const reactFlowWrapper = useRef(null);
    const [reactFlowInstance, setReactFlowInstance] = useState(null);
    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);
  
    const allFetchedNodes = useSelector((state) => state.skilltree.nodes);
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const highestNodeId = useSelector((state) => state.node.highestNodeId);
    const [currentNodeId, setCurrentNodeId] = useState(0);

    let skilltreeId;
    if (skilltree !== null) {
      skilltreeId = skilltree.id;
    }

    useEffect(() => {
      dispatch(fetchHighestNodeIdActionAsync());
      dispatch(fetchAllNodesFromSkilltree(skilltreeId));
    }, [skilltreeId]);

    useEffect(() => {
      convertFetchToNodes();
    }, [allFetchedNodes])

    useEffect(() => {
      setCurrentNodeId(highestNodeId + 1)
    }, [highestNodeId])

    const convertFetchToNodes = () => {
      let tempArray = [];
      allFetchedNodes.map((node) => {
        const tempObj = {
          id: `${node.id}`,
          type: 'custom',
          data: { label: `${node.skill}`, nodeId: `${node.id}` },
          position: { x: node.positionX, y: node.positionY },
        }
        tempArray.push(tempObj);
      })
      setNodes(tempArray);
    }

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
      
      setCurrentNodeId(currentNodeId + 1);

      

      //aanmaken
      const label = `Nieuwe node`
      const description = "";
      const assesmentCriteria = [];
      const learningOutcome = "";
      dispatch(fetchCreateNodeActionAsync(label, description, position.x, position.y ,assesmentCriteria, learningOutcome, skilltreeId))

      //console.log("highest nodeId: "+highestNodeId);
      console.log("current nodeId: "+currentNodeId);

      const newNode = {
        id: `${currentNodeId}`,
        type,
        position,
        data: { label: `${label}`, nodeId: `${currentNodeId}` }
      };

      setNodes((prevNodes) => [...prevNodes, newNode]);
    
    },
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
            fitView
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
