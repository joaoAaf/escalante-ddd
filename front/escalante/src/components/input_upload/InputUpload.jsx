import { useContext, useState } from 'react'
import { GlobalContext } from '../../context/GlobalContext'
import MilitarClient from '../../client/MilitarClient'
import Styles from './styles.module.css'

export default function InputUpload() {

    const { setMilitares, setFeedback } = useContext(GlobalContext)
    
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
            MilitarClient.listarMilitaresEscalaveis(arquivo)
                .then(dados => {
                    setMilitares(dados || [])
                    setCarregandoPlanilha(false)
                    setFeedback({ type: 'success', mensagem: 'Militares importados com sucesso.' })
                })
                .catch(error => {
                    setCarregandoPlanilha(false)
                    setFeedback({ type: 'error', mensagem: error.message })
                })
        } else
            setFeedback({ type: 'info', mensagem: 'Por favor, selecione um arquivo antes de enviar.' })
    }

    const baixarModelo = () => {
        setBaixandoModelo(true)
        MilitarClient.obterPlanilhaModeloMilitares()
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
                setBaixandoModelo(false)
                setFeedback({ type: 'success', mensagem: 'Download do modelo iniciado.' })
            })
            .catch(error => {
                setBaixandoModelo(false)
                setFeedback({ type: 'error', mensagem: error.message })
            })
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
