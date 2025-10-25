import { listarMilitaresEscalaveis } from '../../client/listarMilitaresEscalaveis'
import { obterPlanilhaModeloMilitares } from '../../client/obterPlanilhaModeloMilitares'
import Styles from './styles.module.css'
import { useState } from 'react'

export default function InputUpload({ gerenciarMilitares }) {
    const [nomeArquivo, setNomeArquivo] = useState("Nenhuma seleção.")
    const [arquivo, setArquivo] = useState(null)
    const [carregandoPlanilha, setCarregandoPlanilha] = useState(false)
    const [baixandoModelo, setBaixandoModelo] = useState(false)

    const gerenciarArquivo = evento => {
        if (evento.target.files.length > 0) {
            setNomeArquivo(evento.target.files[0].name)
            setArquivo(evento.target.files[0])
        }
    }

    const enviarArquivo = () => {
        if (arquivo) {
            setCarregandoPlanilha(true)
            listarMilitaresEscalaveis(arquivo)
                .then(dados => gerenciarMilitares(dados || []))
                .finally(() => setCarregandoPlanilha(false))
        } else
            alert("Por favor, selecione um arquivo primeiro.")
    }

    const baixarModelo = () => {
        setBaixandoModelo(true)
        obterPlanilhaModeloMilitares()
            .then(arrayBuffer => {
                if (arrayBuffer) {
                    const blob = new Blob([arrayBuffer],
                        { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' })
                    const url = URL.createObjectURL(blob)
                    const link = document.createElement('a')
                    link.href = url
                    link.download = 'modelo_militares.xlsx'
                    link.click()
                    URL.revokeObjectURL(url)
                }
            })
            .finally(() => setBaixandoModelo(false))
    }

    return (
        <div className={Styles.input_upload}>
            <button
                className={Styles.modelo_btn}
                onClick={baixarModelo}
                disabled={baixandoModelo}
            >
                {baixandoModelo ? "Baixando..." : "Modelo"}
            </button>

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
                disabled={carregandoPlanilha}
            >
                {carregandoPlanilha ? "Carregando..." : "Enviar/Processar"}
            </button>
        </div>
    )
}