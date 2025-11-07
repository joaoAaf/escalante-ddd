import { useState } from 'react'
import './App.css'
import Sidebar from './components/sidebar'
import { Outlet } from 'react-router-dom'
import { GlobalContextProvider } from './context/GlobalContext'

export default function App() {
  return (
    <div className="container">
      <Sidebar />
      <div className='main-content'>
        <GlobalContextProvider>
          <Outlet />
        </GlobalContextProvider>
      </div>
    </div>
  )
}
