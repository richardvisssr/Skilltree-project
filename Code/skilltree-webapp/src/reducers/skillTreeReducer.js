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
        console.log("4", action.payload)
        console.log("5", state.nodes)
        return {
            ...state,
            nodes: [...state.nodes, action.payload],
        }
    default:
        return state;
    }
}
export default skillTreeReducer;
