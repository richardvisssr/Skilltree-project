import React from 'react';
import { useSelector } from 'react-redux';

import SidebarComponent from '../components/sidebarComponent';
import TopbarComponent from '../components/topbarComponent';
// import ReactFlow from 'reactflow';



export default function HomeScreen() {
    const skilltreeSelected = useSelector(state => state.skilltree.skilltreeSelected);
    
    console.log(skilltreeSelected)

    const selectedSkilltree = () => {
        if (skilltreeSelected === true) {
            return (
                <div>
                <TopbarComponent />
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
