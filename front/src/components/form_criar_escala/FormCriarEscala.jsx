import { useState, useContext } from 'react'
import { useNavigate } from 'react-router-dom'
import { GlobalContext } from '../../context/GlobalContext'
import Styles from './styles.module.css'
import EscalaClient from '../../clients/EscalaClient'
import { inserirIds } from '../../utils/geradorIds'

export default function FormCriarEscala() {

    const { militares, setEscala, setFeedback } = useContext(GlobalContext)

    const [dataInicio, setDataInicio] = useState('')
    const [dataFim, setDataFim] = useState('')
    const [diasServico, setDiasServico] = useState(2)
    const [carregandoEscala, setCarregandoEscala] = useState(false)
    const navegar = useNavigate()

    const gerenciarCriacaoEscala = evento => {
        evento.preventDefault()

        const form = evento.currentTarget

        if (!form.checkValidity()) {
                    for (const element of form.elements) {
                if (element.willValidate && !element.checkValidity()) {
                    return setFeedback({ type: 'info', mensagem: element.validationMessage })
                }
            }
        }

        if (dataFim <= dataInicio)
            return setFeedback({ type: 'info', mensagem: 'A data final não pode ser anterior à data inicial.' })

        setCarregandoEscala(true)
        
        const dadosEscala = {
            dataInicio,
            dataFim,
            diasServico,
            militares
        }

        EscalaClient.criarEscalaAutomatica(dadosEscala)
            .then(escala => {
                const lista = (escala || [])
                const comIdsFinal = inserirIds(lista)
                setEscala(comIdsFinal)
                setCarregandoEscala(false)
                setFeedback({ type: 'success', mensagem: 'Escala criada com sucesso.' })
                navegar('/escala')
            })
            .catch(error => {
                setCarregandoEscala(false)
                setFeedback({ type: 'error', mensagem: error.message })
            })
    }

    return (
        <div className={Styles.criar_escala}>
            <h3>Dados para Criação da Escala</h3>
            <form onSubmit={gerenciarCriacaoEscala} noValidate>
                <div>
                    <label>Data Inicial:</label>
                    <input
                        type="date"
                        value={dataInicio}
                        onChange={e => {
                            e.target.setCustomValidity("")
                            setDataInicio(e.target.value)
                        }}
                        required
                        onInvalid={e => e.target.setCustomValidity("Por favor, digite uma data inicial válida.")}
                    />
                </div>

                <div>
                    <label>Data Final:</label>
                    <input
                        type="date"
                        value={dataFim}
                        onChange={e => {
                            e.target.setCustomValidity("")
                            setDataFim(e.target.value)
                        }}
                        required
                        onInvalid={e => e.target.setCustomValidity("Por favor, digite uma data final válida.")}
                    />
                </div>

                <div>
                    <label>Dias de Serviço:</label>
                    <input
                        type="number"
                        value={diasServico}
                        onChange={e => {
                            e.target.setCustomValidity("")
                            setDiasServico(e.target.value)
                        }}
                        required
                        min="1"
                        max="12"
                        step="1"
                        title="Os dias de serviço devem ser um número inteiro positivo."
                        onInvalid={e => e.target.setCustomValidity("Por favor, digite uma quantidade válida de dias de serviço.")}
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
