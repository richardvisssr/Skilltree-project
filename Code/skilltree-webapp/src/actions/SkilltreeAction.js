const API_PATH = process.env.REACT_APP_API_URL;

export function setSkilltreesAction(skilltrees) {
  return {
    type: "skilltree/setSkilltrees",
    payload: skilltrees
  };
}

export function fetchAllSkilltreesActionAsync(userId) {
  return async (dispatch) => {
    const options = {
      method: 'GET',
      mode: 'cors'
    };
    fetch(`${API_PATH}/skilltrees/docenten/${userId}`, options)
      .then(response => response.json())
      .then(function(result) {
        dispatch(setSkilltreesAction(result.skilltrees))
      })
  }
}

export const createSkillTree = (title, description) => {
  return async (dispatch) => {
    const options = {
      method: 'POST',
      mode: 'cors'
    };
  return {
    fetch(`${API_PATH}/skilltrees/create/`, options)
  .then(response => response.json())
        .then(function(result) {
          dispatch(setSkilltreesAction(result.skilltrees))
        })
  }
}

