import Styles from './styles.module.css'

export default function BarraPesquisa() {
    return (
        <div className={Styles.search}>
            <input type="text" placeholder="Pesquisar militares..." />
        </div>
    )
}