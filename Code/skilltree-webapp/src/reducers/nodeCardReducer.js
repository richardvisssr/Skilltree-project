const initialState = {
    components: [],
};

// eslint-disable-next-line default-param-last
const deleteNodeCardReducer = (state = initialState, action) => {
    switch (action.type) {
    case "node/deleteNodeCard":
        return {
            ...state,
            components: state.components.filter((component) => component.id !== action.payload.id),
        };
    default:
        return state;
    }
};

export default deleteNodeCardReducer;
