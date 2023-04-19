//import { Fragment } from 'react'
import { Disclosure} from '@headlessui/react' // Menu, Transition
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline' // BellIcon


const forms = [
    { name: 'name', id: 'name', placeholder: 'Naam'},
    { name: 'description', id: 'description', placeholder: 'Beschrijving'},

]

// function classNames(...classes) {
//   return classes.filter(Boolean).join(' ')
// }

export default function Example() {
  return (
    <Disclosure as="nav" className="bg-gray-800">
      {({ open }) => (
        <>
          <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
            <div className="relative flex h-16 items-center justify-between">
              <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
                {/* Mobile menu button*/}
                <Disclosure.Button className="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                  <span className="sr-only">Open main menu</span>
                  {open ? (
                    <XMarkIcon className="block h-6 w-6" aria-hidden="true" />
                  ) : (
                    <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
                  )}
                </Disclosure.Button>
              </div>
              <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                <div className="flex flex-1">
                  <div className="flex items-center">
                    <img
                      className="block h-8 w-auto lg:hidden"
                      src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"
                      alt="Your Company"
                    />
                    <img
                      className="hidden h-8 w-auto lg:block"
                      src="https://www.stipbike.nl/wp-content/uploads/2020/02/HAN-logo.png"
                      alt="Your Company"
                    />
                  </div>

                  <div className="hidden sm:ml-6 sm:block">
                    <div className="flex space-x-4">
                      <div className="relative rounded-md shadow-sm">
                          {forms.map((item) => (
                          <input
                              type="text"
                              name={item.name}
                              id={item.id} //block w-full 
                              //className="rounded-md border-0 py-1.5 px-2 bg-gray-900 text-gray-900 ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                              className='bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-3 py-2 text-sm font-medium'
                              placeholder={item.placeholder}
                          />
                          ))}
                      </div>
                          
                    </div>
                  </div>
                </div>
                <div className="flex flex-1 items-center justify-center">
                  <button class="bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-5 py-2 text-sm font-medium">Node Aanmaken</button>
                  <button class="bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-5 py-2 text-sm font-medium">Koppelen</button>
                </div>
              </div>
              <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <button class="border-solid border-green-600 border-2 bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-5 py-2 text-sm font-medium">Opslaan</button>
              </div>
            </div>
          </div>
          

          {/* Mobile dropdown menu */}

          {/* <Disclosure.Panel className="sm:hidden">
            <div className="space-y-1 px-2 pb-3 pt-2">
              {navigation.map((item) => (
                <Disclosure.Button
                  key={item.name}
                  as="a"
                  href={item.href}
                  className={classNames(
                    item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                    'block rounded-md px-3 py-2 text-base font-medium'
                  )}
                  aria-current={item.current ? 'page' : undefined}
                >
                  {item.name}
                </Disclosure.Button>
              ))}
            </div>
          </Disclosure.Panel> */}

        </>
      )}
    </Disclosure>
  )
}
