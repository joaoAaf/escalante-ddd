import { useState } from 'react'
import './App.css'
import Militares from './components/militares'
import Sidebar from './components/sidebar'
import Escala from './components/escala'

export default function App() {
  const [telaAtiva, setTelaAtiva] = useState('militares')

  const selecionarTela = () => {
    switch (telaAtiva) {
      case 'militares':
        return <Militares />
      case 'escala':
        return <Escala />
      default:
        return <Militares />
    }
  }

  return (
    <div className="container">
      <Sidebar
        telaAtiva={telaAtiva}
        setTelaAtiva={setTelaAtiva}
      />
      <div className='main-content'>
        {selecionarTela()}
      </div>
    </div>
  )
}
