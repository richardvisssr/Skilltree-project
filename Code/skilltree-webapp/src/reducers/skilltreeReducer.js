const initialState = {
    skilltrees: [],
    currentSkilltree: null,
    newSkilltree: false,
    nodes: [],
    edges: [],
    showDeleteSkilltreeCard: false,
};

function skillTreeReducer(state = initialState, action) {
    switch (action.type) {
    case "skilltree/setSkilltrees":
        return {
            ...state,
            skilltrees: action.payload,
        };
    case "skilltree/setCurrentSkilltree":
        return {
            ...state,
            currentSkilltree: action.payload,
            newSkilltree: false,
        };
    case "skilltree/addSkilltreeTopbar":
        return {
            ...state,
            currentSkilltree: null,
            newSkilltree: !state.newSkilltree,
        };
    case "skilltree/setAllNodes":
        return {
            ...state,
            nodes: action.payload,
        }
    case "skilltree/setAllEdges":
        return {
            ...state,
            edges: action.payload.edges,
        }
    case "skilltree/deleteEdge":
        const newEdges = state.edges.filter((edge) => parseInt(edge.id) !== parseInt(action.payload.edgeId));
        return {
            ...state,
            edges: newEdges,
        }
    case "skilltree/createNode":
        return {
            ...state,
            nodes: [...state.nodes, action.payload],
        }
    case "skilltree/updateNode":
        const updatedNodes = state.nodes.map((node) => {
            if (node.id === action.payload.id) {
            return {
                ...node,
                skill: action.payload.skill,
                description: action.payload.description,
                learningOutcome: action.payload.learningOutcome,
                assessmentCriteria: action.payload.assessmentCriteria,
            };
            }
            return node;
        });
        return {
            ...state,
            nodes: updatedNodes,
        };
    case "skilltree/showDeleteSkilltreeCard":
        return {
            ...state,
            showDeleteSkilltreeCard: true,
        }
    case "skilltree/hideDeleteSkilltreeCard":
        return {
            ...state,
            showDeleteSkilltreeCard: false,
        }
            
    case "skilltree/deleteNode":
        const newNodes = state.nodes.filter((node) => parseInt(node.id) !== parseInt(action.payload.nodeId));
        return {
            ...state,
            nodes: newNodes,
        }
        
    default:
        return state;
    }
}

export default skillTreeReducer;
