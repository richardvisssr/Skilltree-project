const initialState = {
    skilltrees: [],
    currentSkilltree: null,
    newSkilltree: false,
    nodes: [],
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
    default:
        return state;
    }
}
export default skillTreeReducer;
