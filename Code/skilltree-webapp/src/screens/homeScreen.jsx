import React from 'react';
import { useSelector } from 'react-redux';

import SidebarComponent from '../components/sidebarComponent';
import TopbarComponent from '../components/topbarComponent';
import ReactFlowComponent from '../components/ReactFlowComponent';
import NoSkilltreeComponent from '../components/NoSkilltreeComponent';

export default function HomeScreen() {
    const currentSkilltree = useSelector(state => state.skilltree.currentSkilltree);
    const newSkilltree = useSelector(state => state.skilltree.newSkilltree);

    
    const skilltreeSelected = () => {
        if (currentSkilltree !== null || newSkilltree) {
            return (
                <div>
                    <TopbarComponent currentSkilltree={currentSkilltree} newSkilltree={newSkilltree} />
                    <ReactFlowComponent />
                </div>
            )
        } else {
            return (
                <div className='text-center align-middle m-auto'>
                    <NoSkilltreeComponent />
                </div>
            )
        }
    }

    return (
        <div className="flex-row flex w-screen">
                <SidebarComponent />
            <div className="flex flex-col w-full">
                {skilltreeSelected()}
            </div>
        </div>
    )
}
