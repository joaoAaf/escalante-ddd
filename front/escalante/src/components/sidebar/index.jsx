import Styles from './styles.module.css'
import FireFighter from './assets/firefighter.png'
import Notes from './assets/notes.png'

export default function Sidebar({ telaAtiva, setTelaAtiva }) {
    const gerenciodorTela = (evento, nomeTela) => {
        evento.preventDefault()
        setTelaAtiva(nomeTela)
    }

    return (
        <div className={Styles.sidebar}>
            <h1>Escalante</h1>
            <nav>
                <a
                    href="#"
                    title="Militares"
                    className={telaAtiva === 'militares' ? Styles.active : ''}
                    onClick={(evento) => gerenciodorTela(evento, 'militares')}
                >
                    <img src={FireFighter} alt="Militares" />
                    <span>Militares</span>
                </a>
                <a
                    href="#"
                    title="Escala"
                    className={telaAtiva === 'escala' ? Styles.active : ''}
                    onClick={(evento) => gerenciodorTela(evento, 'escala')}
                >
                    <img src={Notes} alt="Escala" />
                    <span>Escala</span>
                </a>
            </nav>
        </div>
    )
}