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

export function setCreateSkillTreeAction(title, description) {
  return {
    type: "skilltree/createSkilltree",
    payload: {title: title, description: description}
  };
}

export const fetchCreateSkillTreeActionAsync = (title, description) => {
  return async (dispatch) => {
    const options = {
      method: 'POST',
      body: JSON.stringify({ title, description }),
      mode: 'cors',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      }
    };
    fetch(`${API_PATH}/skilltrees/create/`, options)
  .then(response => response.json())
        .then(function(result) {
          dispatch(setCreateSkillTreeAction(title, description))
        })
  }
}

