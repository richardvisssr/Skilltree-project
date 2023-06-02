const initialState = {
    nodeId: null,
    showCard: false,
    highestNodeId: null,
    currentNode: null,
};

const NodeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "node/reset":
            return {
                ...state,
                nodeId: null,
                showCard: false,
                highestNodeId: null,
                currentNode: null,
            }
        case "node/showNodeCard":
            return {
                ...state,
                showCard: !state.showCard,
            };
        case "node/highestNodeId":
            return {
                ...state,
                highestNodeId: action.payload
            };
        case "node/setCurrentNode":
            return {
                ...state,
                currentNode: action.payload
            };
    default:
        return state;
    }
};

export default NodeReducer;
