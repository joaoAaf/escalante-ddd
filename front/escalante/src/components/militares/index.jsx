import { useEffect, useState } from 'react'
import BarraPesquisa from '../barra_pesquisa'
import InputUpload from '../input_upload'
import TabelaMilitares from '../tabela_militares'
import FormCriarEscala from '../form_criar_escala'
import Styles from './styles.module.css'
import { obterLocalStorage, salvarLocalStorage } from '../../scripts/persistenciaDados'

export default function Militares({ setEscala, setTelaAtiva }) {
    const [militares, setMilitares] = useState(null)

    const STORAGE_KEY_MILITARES = 'militares'

    obterLocalStorage(STORAGE_KEY_MILITARES, setMilitares)
    salvarLocalStorage(STORAGE_KEY_MILITARES, militares)

    return (
        <div className={Styles.main}>
            <h2>Militares Escalaveis</h2>
            <div className={Styles.upload}>
                <label htmlFor="input_upload" className={Styles.label_upload}>Importe a Planilha dos Militares</label>
                <InputUpload setMilitares={setMilitares} />
            </div>
            <FormCriarEscala
                militares={militares}
                setEscala={setEscala}
                setTelaAtiva={setTelaAtiva}
            />
            <BarraPesquisa />
            <TabelaMilitares
                militares={militares}
                setMilitares={setMilitares}
            />
        </div>
    )
}