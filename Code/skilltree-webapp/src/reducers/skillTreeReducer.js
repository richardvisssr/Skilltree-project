const initialState = {
    skilltrees: [],
    currentSkilltree: null,
    newSkilltree: false,
};

// eslint-disable-next-line default-param-last
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
    default:
        return state;
    }
}
export default skillTreeReducer;
