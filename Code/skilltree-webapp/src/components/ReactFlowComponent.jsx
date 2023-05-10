import React, { useState, useRef, useCallback, useEffect }  from "react";
import { useDispatch, useSelector } from "react-redux";
import ReactFlow, {
  ReactFlowProvider,
  addEdge,
  useNodesState,
  useEdgesState,
  Controls, MarkerType,
} from 'reactflow';

import CustomNode from "./CustomNode";
import FloatingEdge from "./edges/FloatingEdge";
import ConnectionLineStyle from "./edges/ConnectionLineStyle";

import { fetchAllNodesFromSkilltree } from "../actions/SkilltreeAction";
import { fetchCreateNodeActionAsync, fetchHighestNodeIdActionAsync } from "../actions/NodeAction";
import "reactflow/dist/style.css";
import "../styles/styles.css";
import {fetchCreateEdgeActionAsync} from "../actions/EdgeAction";

const defaultViewport = { x: 0, y: 0 };

const edgeTypes = {
    floating: FloatingEdge,
};

const nodeTypes = {
    custom: CustomNode,
};

const defaultEdgeOptions = {
  style: { strokeWidth: 3, stroke: 'black' },
  type: 'floating',
  markerEnd: {
    type: MarkerType.ArrowClosed,
    color: 'black',
  },
};
const connectionLineStyle = {
  strokeWidth: 3,
  stroke: 'black',
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
          data: { label: `${node.skill}` },
          position: { x: node.positionX, y: node.positionY },
        }
        tempArray.push(tempObj);
      })
      setNodes(tempArray);
    }

  const onConnect = useCallback((params) => {
    if(params.source === params.target) {
      return;
    }
    setEdges((eds) => addEdge(params, eds))
  }, []);

    useEffect(() => {
        if(edges.length > 0) {
            const lastEdge = edges[edges.length - 1];
            dispatch(fetchCreateEdgeActionAsync(lastEdge.source, lastEdge.target, skilltreeId));
        }
    }, [edges]);

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

      const newNode = {
        id: `${currentNodeId}`,
        type,
        position,
        data: { label: `Nieuwe node` }
      };

      setNodes((prevNodes) => [...prevNodes, newNode]);

      const description = "";
      const assesmentCriteria = [];
      const learningOutcome = "";
      dispatch(fetchCreateNodeActionAsync(newNode.data.label, description, position.x, position.y ,assesmentCriteria, learningOutcome, skilltreeId))
    }
  );
  
    return (
      <ReactFlowProvider>
        <div className="w-full flex-auto" ref={reactFlowWrapper}>
          <ReactFlow
            nodes={nodes}
            edges={edges}
            edgeTypes={edgeTypes}
            nodeTypes={nodeTypes}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            onConnect={onConnect}
            onInit={setReactFlowInstance}
            onDrop={onDrop}
            onDragOver={onDragOver}
            connectionLineComponent={ConnectionLineStyle}
            connectionLineStyle={connectionLineStyle}
            defaultEdgeOptions={defaultEdgeOptions}
            defaultViewport={defaultViewport}
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
