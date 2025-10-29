import BarraPesquisa from '../barra_pesquisa'
import TabelaEscala from '../tabela_escala'
import Styles from './styles.module.css'
import { obterLocalStorage, salvarLocalStorage } from '../../scripts/persistenciaLocal'
import { useState } from 'react'
import CadastroServico from '../cadastro_servico'

export default function Escala({ escala, setEscala }) {

    const [statusModal, setStatusModal] = useState(false)

    const STORAGE_KEY_ESCALA = 'escala'

    obterLocalStorage(STORAGE_KEY_ESCALA, setEscala)
    salvarLocalStorage(STORAGE_KEY_ESCALA, escala)

    return (
        <div className={Styles.main}>
            <h2>Escala de Serviço</h2>
            <div className={Styles.adicionarServico}>
                <button onClick={() => setStatusModal(true)}>Adicionar Serviço</button>
            </div>
            <BarraPesquisa />
            <TabelaEscala
                escala={escala}
                setEscala={setEscala}
            />
            <CadastroServico
                statusModal={statusModal}
                fecharModal={() => setStatusModal(false)}
            />
        </div>
    )
}