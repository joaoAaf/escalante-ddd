import Styles from './styles.module.css'
import FireFighter from './assets/firefighter.png'
import Notes from './assets/notes.png'

export default function Sidebar() {
    return (
        <div className={Styles.sidebar}>
            <h1>Escalante</h1>
            <nav>
                <a href="#" title="Militares">
                    <img src={FireFighter} alt="Militares" />
                    <span>Militares</span>
                </a>
                <a href="#" title="Escala">
                    <img src={Notes} alt="Escala" />
                    <span>Escala</span>
                </a>
            </nav>
        </div>
    )
}