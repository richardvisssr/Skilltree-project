import React, {useRef, useEffect, useState} from "react";
import { useDispatch, useSelector } from "react-redux";
import ReactFlow, {
  ReactFlowProvider,
  useNodesState,
  useEdgesState,
  Controls, MarkerType,
} from 'reactflow';
import CustomNode from "../../node/users/student/CustomNode";

import FloatingEdge from "../../edges/users/student/FloatingEdge";
import ConnectionLineStyle from "../../edges/ConnectionLineStyle";

import { fetchAllNodesFromSkilltree, fetchAllEdgesFromSkilltree } from "../../../actions/SkilltreeAction";
import "reactflow/dist/style.css";
import "../../../styles/styles.css";

const edgeTypes = {
    floating: FloatingEdge,
};

const nodeTypes = {
    custom: CustomNode,
};


function ReactFlowComponent() {
  const dispatch = useDispatch();

  const reactFlowWrapper = useRef(null);
  const [nodes, setNodes] = useNodesState([]);
  const [edges, setEdges] = useEdgesState([]);

  const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
  const deleteSwitchNode = useSelector((state) => state.skilltree.deleteSwitchNode);
  const deleteSwitchEdge = useSelector((state) => state.skilltree.deleteSwitchEdge);

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

    return (
      <ReactFlowProvider>
        <div className="w-full flex-auto" ref={reactFlowWrapper}>
          <ReactFlow
            nodes={nodes}
            edges={edges}
            edgeTypes={edgeTypes}
            nodeTypes={nodeTypes}
            nodesConnectable={false}
            nodesDraggable={false}
            connectionLineComponent={ConnectionLineStyle}
            connectionLineStyle={connectionLineStyle}
            defaultEdgeOptions={defaultEdgeOptions}
            fitView
            minZoom={0.2}
            maxZoom={4}
          >
            <Controls showInteractive={false}/>
          </ReactFlow>
        </div>
      </ReactFlowProvider>
    );
}

export default ReactFlowComponent;
