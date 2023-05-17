const initialState = {
    skilltrees: [],
    currentSkilltree: null,
    newSkilltree: false,
    nodes: [],
    edges: [],
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
    case "skilltree/createNode":
        return {
            ...state,
            nodes: [...state.nodes, action.payload],
        }
        case "skilltree/updateNode":
            console.log(action.payload.assesmentCriteria,"1");
            const updatedNodes = state.nodes.map((node) => {
              if (node.id === action.payload.id) {
                return {
                  ...node,
                  skill: action.payload.skill,
                  description: action.payload.description,
                  learningOutcome: action.payload.learningOutcome,
                  assesmentCriteria: action.payload.assesmentCriteria,
                };
              }
              return node;
            });
            console.log(action.payload.assesmentCriteria,"2");
            return {
              ...state,
              nodes: updatedNodes,
            };
            
    default:
        return state;
    }
}
export default skillTreeReducer;
