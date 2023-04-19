import React from 'react';

import SidebarComponent from '../components/sidebarComponent';
import TopbarComponent from '../components/topbarComponent';
// import ReactFlow from 'reactflow';

const skilltreeSelected = true;

const selectedSkilltree = () => {
    if (skilltreeSelected === true) {
        return (
            <div>
            <TopbarComponent />
            </div>
        )
    }
}

export default function homeScreen() {
    return (
        <div>
            <div>
                <SidebarComponent />
            </div>
            {selectedSkilltree()}
        </div>
    )
}
