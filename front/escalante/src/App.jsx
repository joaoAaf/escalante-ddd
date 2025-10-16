import { useState } from 'react'
import './App.css'
import Militares from './components/militares'
import Sidebar from './components/sidebar'

function App() {
  const [telaAtiva, setTelaAtiva] = useState('militares')

  return (
    <div className="container">
      <Sidebar
        telaAtiva={telaAtiva}
        setTelaAtiva={setTelaAtiva}
      />
      <div className='main-content'>
        {telaAtiva === 'militares' && <Militares />}
      </div>
    </div>
  )
}

export default App
