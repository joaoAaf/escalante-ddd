import { useState } from 'react'
import BarraPesquisa from '../barra_pesquisa'
import InputUpload from '../input_upload'
import TabelaMilitares from '../tabela_militares'
import Styles from './styles.module.css'

export default function Militares() {
    const [militares, setMilitares] = useState([true])

    const gerenciarMilitares = dados => setMilitares(dados)

    return (
        <div className={Styles.main}>
            <h2>Militares Escalaveis</h2>
            <div className={Styles.upload}>
                <label htmlFor="input_upload" className={Styles.label_upload}>Importe a Planilha dos Militares</label>
                <InputUpload gerenciarMilitares={gerenciarMilitares} />
                <BarraPesquisa />
                <TabelaMilitares militares={militares} />
            </div>
        </div>
    )
}