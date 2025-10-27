import { useEffect, useState } from 'react'
import BarraPesquisa from '../barra_pesquisa'
import InputUpload from '../input_upload'
import TabelaMilitares from '../tabela_militares'
import FormCriarEscala from '../form_criar_escala'
import Styles from './styles.module.css'

export default function Militares({ setEscala, setTelaAtiva }) {
    const [militares, setMilitares] = useState(null)

    const STORAGE_KEY_MILITARES = 'militares'

    useEffect(() => {
        try {
            const militaresArmazenados = localStorage.getItem(STORAGE_KEY_MILITARES)
            if (militaresArmazenados) setMilitares(JSON.parse(militaresArmazenados))
        } catch (error) {
            console.error("Erro ao carregar militares do localStorage:", error)
        }
    }, [])

    useEffect(() => {
        try {
            if (militares === null) localStorage.removeItem(STORAGE_KEY_MILITARES)
            else localStorage.setItem(STORAGE_KEY_MILITARES, JSON.stringify(militares))
        } catch (error) {
            console.error("Erro ao salvar militares no localStorage:", error)
        }
    }, [militares])

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