import { useState } from 'react'
import './App.css'
import Sidebar from './components/sidebar'
import { Outlet } from 'react-router-dom'

export default function App() {
  
  const [escala, setEscala] = useState(null)

  return (
    <div className="container">
      <Sidebar />
      <div className='main-content'>
        <Outlet context={{ escala, setEscala }} />
      </div>
    </div>
  )
}
