const API_PATH = process.env.REACT_APP_API_URL;

export function fetchCreateEdgeActionAsync(edgeId, skilltreeId, sourceId, targetId) {
  return async (dispatch) => {
    const options = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        edgeId, sourceId, targetId, skilltreeId
      }),

    };
    fetch(`${API_PATH}/edges/skilltrees/${skilltreeId}`, options)
        .then((response) => response.json())
  };
}