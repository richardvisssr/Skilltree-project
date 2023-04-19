import React, {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {updateSkillTree} from '../actions/skilltree';


function TopBar() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const dispatch = useDispatch();
    const handleSave = () => {
        dispatch(updateSkillTree(title, description));
    };

    const stateName = useSelector(state => state.skilltree.title);

  return (
      <div>
        <input type="text" placeholder="Naam" onChange={e => setTitle(e.target.value)}/>
        <input type="text" placeholder="Beschrijving" onChange={e => setDescription(e.target.value)}/>
        <button onClick={handleSave}>Opslaan</button>
          <h1>Title: {stateName}</h1>
      </div>
  );
}

export default TopBar;
