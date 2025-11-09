import { useState, useContext } from 'react'
import { GlobalContext } from '../../context/GlobalContext'
import { CadastroServicoContext } from '../../context/CadastroServicoContext'
import Styles from './styles.module.css'
import EscalaClient from '../../client/EscalaClient'

export default function AcoesEscala() {

    const { escala, setFeedback } = useContext(GlobalContext)
    const { setStatusModal } = useContext(CadastroServicoContext)

    const [exportandoEscala, setExportandoEscala] = useState(false)

    const exportarEscalaXLSX = escala => {
        if (!escala || escala.length === 0)
            return setFeedback({ type: 'info', mensagem: 'Não há escala disponível para exportação.' })
        setExportandoEscala(true)
        EscalaClient.exportarEscalaXLSX(escala)
            .then(arrayBuffer => {
                if (arrayBuffer) {
                    const blob = new Blob([arrayBuffer],
                        { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' })
                    const url = URL.createObjectURL(blob)
                    const link = document.createElement('a')
                    link.href = url
                    link.download = `escala_${new Date().toLocaleDateString('sv-SE')}.xlsx`
                    link.click()
                    URL.revokeObjectURL(url)
                }
                setExportandoEscala(false)
                setFeedback({ type: 'success', mensagem: 'Exportação da escala realizada com sucesso. Download iniciado.' })
            })
            .catch(error => {
                setExportandoEscala(false)
                setFeedback({ type: 'error', mensagem: error.message })
            })
    }

    return (
        <div className={Styles.acoesEscala}>
            <button onClick={() => setStatusModal(true)}>Adicionar Serviço</button>
            <button
                onClick={() => exportarEscalaXLSX(escala)}
                disabled={exportandoEscala}
            >
                {exportandoEscala ? "Exportando..." : "Exportar Escala"}
            </button>
        </div>
    )
}