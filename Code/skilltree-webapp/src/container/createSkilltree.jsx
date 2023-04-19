import { connect } from 'react-redux';
import TopBar from './TopBar';
import { updateSkillTree } from './actions';
import React,{Component} from 'react';

function SkillTreeEditor ({ name, description, updateSkillTree }){
    const handleSave = () => {
        updateSkillTree(name, description);
    };

    return (
        <div>
            <TopBar onSave={handleSave} />
            {/* implementeer de rest van de skilltree-editor hier */}
        </div>
    );
}

const mapStateToProps = state => {
    return {
        name: state.skillTree.name,
        description: state.skillTree.description
    };
};

const mapDispatchToProps = {
    updateSkillTree
};

export default connect(mapStateToProps, mapDispatchToProps)(SkillTreeEditor);
