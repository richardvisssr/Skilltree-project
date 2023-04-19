import React from 'react';

import SidebarComponent from '../components/SidebarComponent';
import TopbarComponent from '../components/TopbarComponent';
import ReactFlow from 'reactflow';

const skilltreeSelected = true;

const selectedSkilltree = () => {
    if (skilltreeSelected === true) {
        return (
            <div>
            <TopbarComponent />
            <ReactFlow />
            </div>
        )
    }
}

export default function homeScreen() {
    return (
        <div>
            <SidebarComponent />
            {selectedSkilltree()}
        </div>
    )
}