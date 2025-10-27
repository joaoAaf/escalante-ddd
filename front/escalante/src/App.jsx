import { useState } from 'react'
import './App.css'
import Militares from './components/militares'
import Sidebar from './components/sidebar'
import Escala from './components/escala'

export default function App() {
  const [telaAtiva, setTelaAtiva] = useState('militares')
  const [escala, setEscala] = useState(null)

  const componenteMilitares = <Militares setEscala={setEscala} setTelaAtiva={setTelaAtiva} />
  const componenteEscala = <Escala escala={escala} setEscala={setEscala} />

  const selecionarTela = () => {
    switch (telaAtiva) {
      case 'militares':
        return componenteMilitares
      case 'escala':
        return componenteEscala
      default:
        return componenteMilitares
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
