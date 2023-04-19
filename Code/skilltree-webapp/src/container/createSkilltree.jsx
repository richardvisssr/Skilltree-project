import {connect, useSelector} from 'react-redux';
import TopBar from 'Code/skilltree-webapp/src/components/Topbar.jsx';
import { useDispatch } from 'react-redux';
import {updateSkillTree} from "../actions/skilltree";

function SkillTreeEditor ({ title, description, updateSkillTree }){

    const dispatch = useDispatch();
    const handleSave = () => {
        dispatch(createSkillTree(title, description));
    };

    return (
        <div>
            <TopBar onSave={handleSave} />
            {/* implementeer de rest van de skilltree-editor hier */}
        </div>
    );
}

export default SkillTreeEditor;