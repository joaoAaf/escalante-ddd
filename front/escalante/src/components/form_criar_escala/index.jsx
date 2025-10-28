import Styles from './styles.module.css'
import { useState } from 'react'
import EscalaClient from '../../client/EscalaClient'

export default function FormCriarEscala({ militares, setEscala, setTelaAtiva }) {

    const [dataInicio, setDataInicio] = useState('')
    const [dataFim, setDataFim] = useState('')
    const [diasServico, setDiasServico] = useState(2)
    const [carregandoEscala, setCarregandoEscala] = useState(false)

    const gerenciarCriacaoEscala = async evento => {
        evento.preventDefault()
        if (!dataInicio || !dataFim)
            alert("Por favor, preencha a data inicial e final.")
        else if (isNaN(diasServico) || diasServico <= 0)
            alert("Por favor, os dias de serviço devem ser maiores que zero.")
        else if (!militares || militares.length === 0)
            alert("Não há militares disponíveis para criar a escala. Por favor, importe a planilha de militares.")
        else {
            setCarregandoEscala(true)
            const dadosEscala = {
                dataInicio,
                dataFim,
                diasServico,
                militares
            }
            EscalaClient.criarEscalaAutomatica(dadosEscala)
                .then(escala => setEscala(escala || []))
                .finally(() => {
                    setCarregandoEscala(false)
                    setTelaAtiva('escala')
                })
        }
    }

    return (
        <div className={Styles.criar_escala}>
            <h3>Dados para Criação da Escala</h3>
            <form onSubmit={gerenciarCriacaoEscala}>
                <div>
                    <label htmlFor="data-inicio">Data Inicial:</label>
                    <input type="date" id="data-inicio" value={dataInicio} onChange={e => setDataInicio(e.target.value)} />
                </div>

                <div>
                    <label htmlFor="data-fim">Data Final:</label>
                    <input type="date" id="data-fim" value={dataFim} onChange={e => setDataFim(e.target.value)} />
                </div>

                <div>
                    <label htmlFor="dias-servico">Dias de Serviço:</label>
                    <input type="number" id="dias-servico" value={diasServico} onChange={e => setDiasServico(e.target.value)} />
                </div>
                <div>
                    <button
                        type="submit"
                        disabled={carregandoEscala}
                    >
                        {carregandoEscala ? "Criando..." : "Criar Escala"}
                    </button>
                </div>
            </form>
        </div>
    )
}
