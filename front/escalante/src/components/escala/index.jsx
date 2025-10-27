import BarraPesquisa from '../barra_pesquisa'
import TabelaEscala from '../tabela_escala'
import Styles from './styles.module.css'
import { obterLocalStorage, salvarLocalStorage } from '../../scripts/persistenciaDados'

export default function Escala({ escala, setEscala }) {

    const STORAGE_KEY_ESCALA = 'escala'

    obterLocalStorage(STORAGE_KEY_ESCALA, setEscala)
    salvarLocalStorage(STORAGE_KEY_ESCALA, escala)

    return (
        <div className={Styles.main}>
            <h2>Escala de Serviço</h2>
            <BarraPesquisa />
            <TabelaEscala
                escala={escala}
            />
        </div>
    )
}