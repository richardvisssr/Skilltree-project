import React from 'react';
import { useSelector } from 'react-redux';

import SidebarComponent from '../components/sidebarComponent';
import TopbarComponent from '../components/topbarComponent';
import ReactFlowComponent from '../components/ReactFlowComponent';
// import ReactFlow from 'reactflow';



export default function HomeScreen() {
    const currentSkilltree = useSelector(state => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector(state => state.skilltree.newSkilltree);

    
    const topbar = () => {
        if (currentSkilltree !== null || newSkilltree) {
            return (
                <TopbarComponent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
            )
        }
    }

    return (
        <div className="flex-row flex">
                <SidebarComponent />
            <div className="flex flex-col">
                {topbar()}
                <ReactFlowComponent />
            </div>
        </div>
    )
}
