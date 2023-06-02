
const API_PATH = process.env.REACT_APP_API_URL;

export function fetchCreateEdgeActionAsync(sourceId, targetId, skillTreeID, edgeId) {
  return async (dispatch) => {
    const options = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({edgeId, targetId, sourceId, skillTreeID} ),

    };
    return fetch(`${API_PATH}/edges/skilltrees/${skillTreeID}`, options)
        .then((response) => response.json())
  };
}

export function deleteEdgeAction() {
  return {
    type: "skilltree/deleteEdge"
  };
}

export function fetchDeleteEdgeActionAsync(id) {
  return async (dispatch) => {
        const options = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        };
        fetch(`${API_PATH}/edges/${id}`, options)
            .then(() => dispatch(deleteEdgeAction()));
    };
}
