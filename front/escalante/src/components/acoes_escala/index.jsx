import { useState, useContext } from 'react'
import { GlobalContext } from '../../context/GlobalContext'
import { CadastroServicoContext } from '../../context/CadastroServicoContext'
import Styles from './styles.module.css'
import EscalaClient from '../../client/EscalaClient'

export default function AcoesEscala() {

    const { escala } = useContext(GlobalContext)
    const { setStatusModal } = useContext(CadastroServicoContext)
    
    const [exportandoEscala, setExportandoEscala] = useState(false)

    const exportarEscalaXLSX = escala => {
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
            })
            .catch(() => setExportandoEscala(false))
            .finally(() => setExportandoEscala(false))
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