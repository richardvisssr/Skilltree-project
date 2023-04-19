//import { Fragment } from 'react'
import { Disclosure} from '@headlessui/react' // Menu, Transition
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline' // BellIcon


const forms = [
    { name: 'name', id: 'name', placeholder: 'Titel'},
    { name: 'description', id: 'description', placeholder: 'Beschrijving'},
]

const buttons = [
    { text: 'Node Aanmaken' },
    { text: 'Koppelen' }
]

export default function topbarComponent() {
  return (
    <Disclosure as="nav" className="bg-gray-800">
      {({ open }) => (
        <>
          <div className="ml-60 mr-auto max-w-7xl px-2 sm:px-6 lg:px-8">
            <div className="relative flex h-16 items-center justify-between">
              <div className="absolute inset-y-0 left-0 flex items-center lg:hidden">
                {/* Mobile menu button*/}
                <Disclosure.Button className="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                  <span className="sr-only">Open menu</span>
                  {open ? (
                    <XMarkIcon className="block h-6 w-6" aria-hidden="true" />
                  ) : (
                    <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
                  )}
                </Disclosure.Button>
              </div>
              <div className="flex flex-1 items-center justify-center">
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
              
                  <div className="hidden lg:ml-6 lg:block w-fit">
                    <div className="flex space-x-4 w-fit">
                      <div className="relative rounded-md shadow-sm">
                          {forms.map((item, index) => (
                          <input
                              type="text"
                              name={item.name}
                              id={item.id}
                              className='w-2/5 bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-3 py-2 text-sm font-medium'
                              placeholder={item.placeholder}
                              key={index}
                          />
                          ))}
                      </div>
                          
                    </div>
                  </div>
                </div>
                <div className="hidden lg:ml-6 lg:block">
                  <div className="flex items-center justify-center">
                    {buttons.map((item, index) => (
                          <button key={index} className="bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md w-fit mx-3 px-5 py-2 text-sm font-medium">{item.text}</button>
                    ))}
                  </div>
                </div>
              </div>
              <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <button className="border-solid border-green-600 border-2 bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-5 py-2 text-sm font-medium">Opslaan</button>
              </div>
            </div>
          </div>
          {/* Mobile dropdown menu */}
          <Disclosure.Panel className="md:hidden">
            <div className="space-y-1 px-2 pb-3 pt-2">
              {forms.map((item, index) => (
              <input
                  type="text"
                  name={item.name}
                  id={item.id}
                  className='flex bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md mx-3 px-3 py-2 text-sm font-medium'
                  placeholder={item.placeholder}
                  key={index}
              />
              ))}
              {buttons.map((item, index) => (
                    <button key={index} className="flex bg-gray-900 text-gray-300 hover:bg-gray-700 hover:text-white rounded-md w-fit mx-3 px-5 py-2 text-sm font-medium">{item.text}</button>
              ))}
            </div>
          </Disclosure.Panel>
        </>
      )}
    </Disclosure>
  )
}
