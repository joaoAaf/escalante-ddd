import { listarMilitaresEscalaveis } from '../../../client/listarMilitaresEscalaveis'
import Styles from './styles.module.css'
import { useState } from 'react'

export default function InputUpload({ gerenciarMilitares }) {
    const [nomeArquivo, setNomeArquivo] = useState("Nenhum arquivo selecionado.")
    const [arquivo, setArquivo] = useState(null)
    const [carregando, setCarregando] = useState(false)

    const gerenciarArquivo = evento => {
        if (evento.target.files.length > 0) {
            setNomeArquivo(evento.target.files[0].name)
            setArquivo(evento.target.files[0])
        }
    }

    const enviarArquivo = () => {
        if (arquivo) {
            setCarregando(true)
            listarMilitaresEscalaveis(arquivo)
                .then(dados => gerenciarMilitares(dados || []))
                .finally(() => setCarregando(false))
        } else
            alert("Por favor, selecione um arquivo primeiro.")
    }

    return (
        <div className={Styles.input_upload}>
            <input
                type="file"
                id="input_upload"
                onChange={gerenciarArquivo}
                hidden
                accept=".xlsx"
            />

            <label htmlFor="input_upload" className={Styles.browse_btn}>Importar</label>

            <span className={Styles.file_name}>{nomeArquivo}</span>

            <button
                className={Styles.upload_btn}
                onClick={enviarArquivo}
                disabled={carregando}
            >
                {carregando ? "Carregando..." : "Enviar/Processar"}
            </button>
        </div>
    )
}