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
import { fetchallEdgesFromSkilltree } from "../actions/EdgeAction";
import "reactflow/dist/style.css";
import "../styles/styles.css";
import {fetchCreateEdgeActionAsync} from "../actions/EdgeAction";

const edgeTypes = {
    floating: FloatingEdge,
};

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
    const allFetchedEdges = useSelector((state) => state.skilltree.edges);
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const highestNodeId = useSelector((state) => state.node.highestNodeId);
    const showCard = useSelector((state) => state.node.showCard);
    const [currentNodeId, setCurrentNodeId] = useState(0);
    const [deletedEdge, setDeletedEdge] = useState(false);

  const deleteEdge = (id) => {
    setEdges((eds) => eds.filter((e) => e.id !== id));
  }

  const defaultEdgeOptions = {
    style: { strokeWidth: 3, stroke: 'black' },
    type: 'floating',
    markerEnd: {
      type: MarkerType.ArrowClosed,
      color: 'black',
    },
    data: { deleteEdge: deleteEdge, setDeletedEdge: setDeletedEdge}
  };
  const connectionLineStyle = {
    strokeWidth: 3,
    stroke: 'black',
  };



    let skilltreeId;
    if (skilltree !== null) {
      skilltreeId = skilltree.id;
    }

    useEffect(() => {
      dispatch(fetchHighestNodeIdActionAsync());
      dispatch(fetchAllNodesFromSkilltree(skilltreeId));
    }, [skilltreeId, showCard]);



  const convertFetchToEdges = () => {
    let tempArray = [];
    allFetchedEdges.map((edge) => {
      const tempObj = {
        id: `${edge.edgeId}`,
        source: `${edge.sourceId}`,
        target: `${edge.targetId}`,
        type: 'floating',
        style: { strokeWidth: 3, stroke: 'black' },
        markerEnd: {
            type: MarkerType.ArrowClosed,
            color: 'black',
        },
        data: { deleteEdge: deleteEdge, setDeletedEdge: setDeletedEdge}
      }
      tempArray.push(tempObj);
    })
    setEdges(tempArray);
  }

  useEffect(() => {
      convertFetchToNodes();
      convertFetchToEdges();
    }, [allFetchedNodes, allFetchedEdges])

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

  const onConnect = useCallback((params) => {
    if(params.source === params.target) {
      return;
    }
    setEdges((eds) => addEdge(params, eds))
  }, []);

  useEffect(() => {
    if(!deletedEdge) {
      let lastFetchedEdge = allFetchedEdges[allFetchedEdges.length - 1];
      if (edges.length > 0) {
        const lastEdge = edges[edges.length - 1];
        if (lastFetchedEdge !== undefined && lastEdge.id === lastFetchedEdge.edgeId) {
          return;
        }
        dispatch(fetchCreateEdgeActionAsync(lastEdge.source, lastEdge.target, skilltreeId, lastEdge.id));
      }
    } else {
        setDeletedEdge(false);
    }
  }, [edges])


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
        data: { label: `Nieuwe node`, nodeId: `${currentNodeId}` }
      };

      setNodes((prevNodes) => [...prevNodes, newNode]);

      const description = "";
      const assesmentCriteria = [];
      const learningOutcome = "";
      dispatch(fetchCreateNodeActionAsync(newNode.data.label, description, position.x, position.y ,assesmentCriteria, learningOutcome, skilltreeId))
    },
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
