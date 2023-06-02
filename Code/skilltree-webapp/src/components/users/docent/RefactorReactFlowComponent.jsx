import ReactFlow, {addEdge, Controls, MarkerType, ReactFlowProvider, useEdgesState, useNodesState} from "reactflow";
import ConnectionLineStyle from "../../edges/ConnectionLineStyle";
import React, {useCallback, useEffect, useRef, useState} from "react";
import FloatingEdge from "../../edges/users/docent/FloatingEdge";
import CustomNode from "../../node/users/docent/CustomNode";
import {useDispatch, useSelector} from "react-redux";
import { fetchAllNodesFromSkilltree, fetchallEdgesFromSkilltree } from "../../../actions/SkilltreeAction";
import { fetchCreateEdgeActionAsync } from "../../../actions/EdgeAction";
import { fetchCreateNodeActionAsync } from "../../../actions/NodeAction";

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
      fetchNodes();
      fetchEdges();
    }
  }, [skilltree]);

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
    const result = await dispatch(fetchallEdgesFromSkilltree(skilltree.id));
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
        // data: { deleteEdge: deleteEdge, setDeletedEdge: setDeletedEdge}
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
