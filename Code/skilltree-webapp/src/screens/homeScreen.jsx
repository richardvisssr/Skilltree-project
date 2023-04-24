import React from 'react';
import { useSelector } from 'react-redux';

import SidebarComponent from '../components/sidebarComponent';
import TopbarComponent from '../components/topbarComponent';
// import ReactFlow from 'reactflow';



export default function HomeScreen() {
    const currentSkilltree = useSelector(state => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector(state => state.skilltree.newSkilltree);

    
    const selectedSkilltree = () => {
        if (currentSkilltree !== null || newSkilltree) {
            return (
                <div>
                    <TopbarComponent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
                </div>
            )
        }
    }

    return (
        <div>
            <div>
                <SidebarComponent />
            </div>
            {selectedSkilltree()}
        </div>
    )
}
