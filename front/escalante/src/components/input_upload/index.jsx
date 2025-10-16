import Styles from './styles.module.css'
import { useState } from 'react'

export default function InputUpload() {
    const [nomeArquivo, setNomeArquivo] = useState("Nenhum arquivo selecionado.")

    const gerenciarNomeArquivo = (evento) => {
        if (evento.target.files.length > 0)
            setNomeArquivo(evento.target.files[0].name)
    }

    return (
        <div className={Styles.input_upload}>
            <input type="file" id="input_upload" onChange={gerenciarNomeArquivo} hidden />

            <label htmlFor="input_upload" className={Styles.browse_btn}>Importar</label>

            <span className={Styles.file_name}>{nomeArquivo}</span>

            <button className={Styles.upload_btn}>Enviar/Processar</button>
        </div>
    )
}