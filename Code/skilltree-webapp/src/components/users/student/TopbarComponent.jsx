import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Disclosure} from '@headlessui/react' // Menu, Transition
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline' // BellIcon
import { clearStudentCardAction } from '../../../actions/StudentAction';


export default function TopbarComponent() {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const newSkilltree = useSelector((state) => state.skilltree.newSkilltree);
    const currentSkilltree = useSelector((state) => state.skilltree.currentSkilltree);

    const dispatch = useDispatch();

        useEffect(() => {
            if (newSkilltree) {
                setTitle("");
                setDescription("");
            } else {
                setTitle(currentSkilltree.title);
                setDescription(currentSkilltree.description);
            }
            dispatch(clearStudentCardAction());
        }, [currentSkilltree, newSkilltree]);

        return (
            <Disclosure as="nav" className="bg-gray-50 dark:bg-gray-800">
                {({ open }) => (
                    <>
                        <div className="mr-auto px-2 sm:px-6 lg:px-8">
                            <div className="relative flex h-16 items-center justify-between">
                                <div className="inset-y-0 left-0 flex items-center lg:hidden">
                                    {/* Mobile menu button */}
                                    <Disclosure.Button
                                        className="
                                            inline-flex
                                            items-center
                                            justify-center
                                            rounded-md
                                            p-2
                                            text-gray-400
                                            focus:outline-none
                                            focus:ring-2
                                            focus:ring-inset
                                            focus:ring-white"
                                    >
                                        <span className="sr-only">Open menu</span>
                                        {open ? (
                                            <XMarkIcon className="block h-6" aria-hidden="true" />
                                        ) : (
                                            <Bars3Icon className="block h-6" aria-hidden="true" />
                                        )}
                                    </Disclosure.Button>
                                </div>
                                <div className="flex flex-1 items-center justify-between">
                                    <div className="flex w-3/5">
                                        <div className="flex items-center">
                                            <img
                                                className="block h-8 lg:hidden"
                                                src="https://www.stipbike.nl/wp-content/uploads/2020/02/HAN-logo.png"
                                                alt="HAN"
                                            />
                                            <img
                                                className="hidden h-8 lg:block"
                                                src="https://www.stipbike.nl/wp-content/uploads/2020/02/HAN-logo.png"
                                                alt="HAN"
                                            />
                                        </div>
                                        <div className="hidden lg:ml-6 lg:block">
                                            <div className="flex space-x-4">
                                                <div className="relative rounded-md shadow-sm">
                                                    <input
                                                        type="text"
                                                        value={title}
                                                        name="title"
                                                        id="title"
                                                        className="rounded-md mx-3 py-2 text-sm font-medium bg-gray-100 text-black dark:bg-gray-900 dark:text-gray-300 px-3 w-2/5"
                                                        placeholder="Titel"
                                                        disabled
                                                    />
                                                    <input
                                                        type="text"
                                                        value={description}
                                                        name="description"
                                                        id="description"
                                                        className="rounded-md mx-3 py-2 text-sm font-medium bg-gray-100 text-black dark:bg-gray-900 dark:text-gray-300 px-3 w-2/5"
                                                        placeholder="Beschrijving"
                                                        disabled
                                                    />
                                                </div>



                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/* Mobile dropdown menu */}
                        <Disclosure.Panel className="lg:hidden">
                            <div className="flex flex-row">
                                <div className="space-y-1 px-2 pb-3 pt-2">
                                    <input
                                        type="text"
                                        value={title}
                                        name="title"
                                        id="title"
                                        className="rounded-md mx-3 py-2 text-sm font-medium bg-gray-100 text-black dark:bg-gray-900 dark:text-gray-300 px-3"
                                        placeholder="Titel"
                                        disabled
                                    />
                                    <input
                                        type="text"
                                        value={description}
                                        name="description"
                                        id="description"
                                        className="rounded-md mx-3 py-2 text-sm font-medium bg-gray-100 text-black dark:bg-gray-900 dark:text-gray-300 px-3"
                                        placeholder="Beschrijving"
                                        disabled
                                    />
                                </div>
                            </div>
                        </Disclosure.Panel>
                    </>
                )}
            </Disclosure>
        );
}
