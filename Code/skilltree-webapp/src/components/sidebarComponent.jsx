import React from 'react';
import '../styles/styles.css';

const skilltrees = [
    {
        title: 'Skilltree 1',
        link: '/test',
    },
    {
        title: 'Skilltree 2',
        link: '/test',
    },
    {
        title: 'Skilltree 3',
        link: '/test',
    },
    {
        title: 'Skilltree 4',
        link: '/test',
    },
];

const skilltreeList = () => {
    const buttons = skilltrees.map((skilltree, index) =>
        <a href={skilltree.link} class="flex items-center p-2 text-gray-900 transition duration-75 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-700 dark:text-white group">
            <span class="ml-4" key={index}>{skilltree.title}</span>
        </a>)
    return (<li>
        {buttons}
    </li>)
}

export default function testComponent() {
    return (
        <div>
            <aside id="separator-sidebar" class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
                <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
                    <ul class="space-y-2 font-medium">
                        <li>
                            <a href="/home" class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                                <span class="ml-12">Voeg Skilltree toe</span>
                                <span class="mb-1 ml-12 text-3xl">+</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="pt-4 mt-4 space-y-2 font-medium border-t border-gray-200 dark:border-gray-700">
                        {skilltreeList()}
                    </ul>
                </div>
            </aside>
        </div>
    )
}