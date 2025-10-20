import { listarMilitaresEscalaveis } from '../../../client/listarMilitaresEscalaveis'
import Styles from './styles.module.css'
import { useState } from 'react'

export default function InputUpload() {
    const [nomeArquivo, setNomeArquivo] = useState("Nenhum arquivo selecionado.")
    const [arquivo, setArquivo] = useState(null)

    const gerenciarArquivo = (evento) => {
        if (evento.target.files.length > 0) {
            setNomeArquivo(evento.target.files[0].name)
            setArquivo(evento.target.files[0])
        }
    }

    const enviarArquivo = () => {
        if (arquivo) {
            const data = listarMilitaresEscalaveis(arquivo)
            data.then(res => console.log(res))
        } else
            alert("Por favor, selecione um arquivo primeiro.")
    }

    return (
        <div className={Styles.input_upload}>
            <input type="file" id="input_upload" onChange={gerenciarArquivo} hidden />

            <label htmlFor="input_upload" className={Styles.browse_btn}>Importar</label>

            <span className={Styles.file_name}>{nomeArquivo}</span>

            <button className={Styles.upload_btn} onClick={enviarArquivo}>Enviar/Processar</button>
        </div>
    )
}