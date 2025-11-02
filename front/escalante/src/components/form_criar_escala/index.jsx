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
                    <label>Data Inicial:</label>
                    <input
                        type="date"
                        value={dataInicio}
                        onChange={e => setDataInicio(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Data Final:</label>
                    <input
                        type="date"
                        value={dataFim}
                        onChange={e => setDataFim(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Dias de Serviço:</label>
                    <input
                        type="number"
                        value={diasServico}
                        onChange={e => setDiasServico(e.target.value)}
                        required
                        min="1"
                        max="12"
                        step="1"
                        title="Os dias de serviço devem ser um número inteiro positivo."
                    />
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
