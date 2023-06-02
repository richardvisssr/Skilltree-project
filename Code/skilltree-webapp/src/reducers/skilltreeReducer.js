const initialState = {
    skilltrees: [],
    currentSkilltree: null,
    newSkilltree: false,
    deleteSwitchNode: false,
    deleteSwitchEdge: false,
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
    case "skilltree/deleteEdge":
        return {
            ...state,
            deleteSwitchEdge: !state.deleteSwitchEdge,
        }
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
        return {
            ...state,
            deleteSwitchNode: !state.deleteSwitchNode,
        }
    case "skilltree/deleteSkilltree":
        const newTrees = state.skilltrees.filter((skilltree) => parseInt(skilltree.id) !== parseInt(action.payload.skilltreeId));
        return {
            ...state,
            skilltrees: newTrees,
        }
    default:
        return state;
    }
}

export default skillTreeReducer;
