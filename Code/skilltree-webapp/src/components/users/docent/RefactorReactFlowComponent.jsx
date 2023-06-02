import ReactFlow, {addEdge, Controls, MarkerType, ReactFlowProvider, useEdgesState, useNodesState} from "reactflow";
import ConnectionLineStyle from "../../edges/ConnectionLineStyle";
import React, {useCallback, useEffect, useRef, useState} from "react";
import FloatingEdge from "../../edges/users/docent/FloatingEdge";
import CustomNode from "../../node/users/docent/CustomNode";
import {useDispatch, useSelector} from "react-redux";
import { fetchAllNodesFromSkilltree, fetchAllEdgesFromSkilltree } from "../../../actions/SkilltreeAction";
import { fetchCreateEdgeActionAsync } from "../../../actions/EdgeAction";
import {fetchAllNodesPositionsActionAsync, fetchCreateNodeActionAsync} from "../../../actions/NodeAction";
import LinkStudentComponent from "./LinkStudentComponent";

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

  const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
  const deleteSwitchNode = useSelector((state) => state.skilltree.deleteSwitchNode);
  const deleteSwitchEdge = useSelector((state) => state.skilltree.deleteSwitchEdge);
  const showStudentCard = useSelector((state) => state.student.showCard);

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

  useEffect(() => {
    if (skilltree !== null) {
      fetchEdges();
    }
  }, [skilltree, deleteSwitchEdge]);

  useEffect(() => {
    if (skilltree !== null) {
        fetchNodes();
    }
  }, [skilltree, deleteSwitchNode]);

  const fetchNodes = async () => {
    const result = await dispatch(fetchAllNodesFromSkilltree(skilltree.id));
    if (result) {
      setNodes(convertFetchToNodes(result));
    }
  };

  const convertFetchToNodes = (fetchedNodes) => {
    const nodes = [];
    fetchedNodes.map((node) => {
      const tempObj = {
        id: `${node.id}`,
        type: 'custom',
        data: { label: `${node.skill}`, nodeId: `${node.id}` },
        position: { x: node.positionX, y: node.positionY },
      }
      nodes.push(tempObj);
    })
    return nodes;
  }

  const fetchEdges = async () => {
    const result = await dispatch(fetchAllEdgesFromSkilltree(skilltree.id));
    if (result) {
      setEdges(convertFetchToEdges(result));
    }
  }

  const convertFetchToEdges = (fetchedEdges) => {
    const edges = [];
    fetchedEdges.map((edge) => {
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
      }
      edges.push(tempObj);
    })
    return edges;
  }

  const onDragOver = useCallback((event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = 'move';
  }, []);

  const onDrop = useCallback(
      async (event) => {
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

        //aanmaken
        const label = `Nieuwe node`
        const description = "";
        const assessmentCriteria = [];
        const learningOutcome = "";
        await dispatch(fetchCreateNodeActionAsync(label, description, position.x, position.y ,assessmentCriteria, learningOutcome, skilltree.id));
        await fetchNodes();
      }, [reactFlowInstance]
  );

  const onConnect = useCallback(
  async (params) => {
      if(params.source === params.target) {
        return;
      }
      const edgeId = `${params.source}-${params.target}`;

      if(edges.find((edge) => edge.id === edgeId)) {
        return;
      }

      await dispatch(fetchCreateEdgeActionAsync(params.source, params.target, skilltree.id, edgeId));
      await fetchEdges();
  }, [edges]);

  const onPositionClick = () => {
    const tempArray = [];
    nodes.map((node) => {
      const tempObj = {
        id: `${node.id}`,
        positionX: node.position.x,
        positionY: node.position.y,
      }
      tempArray.push(tempObj);
    })
    dispatch(fetchAllNodesPositionsActionAsync(skilltree.id, tempArray));
  };

  const showStudentCardComponent = () => {
    if (showStudentCard) {
      return (
          <LinkStudentComponent />
      )
    }
    return null;
  }

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
        <button
            className="
              absolute
              bottom-0
              right-0 m-4
              bg-blue-500
              hover:bg-blue-700
              text-white
              font-bold py-2
              px-4 rounded"
            onClick={onPositionClick}
        >
          Update Posities
        </button>
      </div>
      {showStudentCardComponent()}
    </ReactFlowProvider>
  );
}

export default ReactFlowComponent;
