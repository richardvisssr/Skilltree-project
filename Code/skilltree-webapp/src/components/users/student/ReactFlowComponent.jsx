import React, { useRef, useEffect }  from "react";
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

import { fetchAllNodesFromSkilltree } from "../../../actions/SkilltreeAction";
import { fetchHighestNodeIdActionAsync } from "../../../actions/NodeAction";
import { fetchallEdgesFromSkilltree } from "../../../actions/EdgeAction";
import { fetchAllStudentsFromSkilltreeActionAsync } from "../../../actions/StudentAction";
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
    const allFetchedNodes = useSelector((state) => state.skilltree.nodes);
    const allFetchedEdges = useSelector((state) => state.skilltree.edges);
    const skilltree = useSelector((state) => state.skilltree.currentSkilltree);
    const highestNodeId = useSelector((state) => state.node.highestNodeId);
    const showCard = useSelector((state) => state.node.showCard);

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

    let skilltreeId;
    if (skilltree !== null) {
      skilltreeId = skilltree.id;
    }

    useEffect(() => {
      dispatch(fetchHighestNodeIdActionAsync());
      dispatch(fetchAllNodesFromSkilltree(skilltreeId));
      dispatch(fetchallEdgesFromSkilltree(skilltreeId));
      dispatch(fetchAllStudentsFromSkilltreeActionAsync(skilltreeId))
    }, [skilltreeId,showCard]);

    const convertFetchToEdges = () => {
        const tempArray = [];
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
    }, [highestNodeId])

    const convertFetchToNodes = () => {
      const tempArray = [];
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
