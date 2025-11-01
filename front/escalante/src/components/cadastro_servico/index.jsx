import Styles from './styles.module.css'
import { useState } from 'react'
import { ordenarEscala } from '../../scripts/ordenacaoEscala'

export default function CadastroServico({ statusModal, fecharModal, escala, setEscala }) {
    const servicoModelo = {
        dataServico: '',
        matricula: '',
        nomePaz: '',
        patente: '',
        antiguidade: '',
        funcao: '',
        folga: ''
    }

    const [servico, setServico] = useState(servicoModelo)

    const cadastrarServico = evento => {
        evento.preventDefault()
        let novaEscala = [...escala, servico]
        novaEscala = ordenarEscala(novaEscala)
        setEscala(novaEscala)
        setServico(servicoModelo)
        fecharModal()
    }

    const converterMaiusculas = nome => {
        return nome.toUpperCase()
    }

    const removerEspacosExtras = nome => {
        return nome.trim().replace(/\s+/g, ' ')
    }

    if (!statusModal) {
        return null
    }

    return (
        <div className={Styles.modal} onClick={fecharModal}>

            <div onClick={e => e.stopPropagation()}>

                <h2>Adicionar Serviço</h2>

                <form onSubmit={cadastrarServico}>

                    <label>Data do Serviço:</label>
                    <input
                        type="date"
                        value={servico.dataServico}
                        onChange={e => setServico({ ...servico, dataServico: e.target.value })}
                        required
                    />

                    <label>Matrícula:</label>
                    <input
                        type="text"
                        placeholder="Ex: 1234567X"
                        value={servico.matricula}
                        onChange={e => setServico({ ...servico, matricula: e.target.value })}
                        required
                        pattern="[A-Z0-9]{8,8}"
                        title="O código deve conter 8 caracteres, sendo apenas letras maiúsculas e números."
                        maxLength="8"
                        minLength="8"
                    />

                    <label>Militar Escalado:</label>
                    <input
                        type="text"
                        placeholder="Ex: FULANO DE TAL"
                        value={servico.nomePaz}
                        onChange={e => setServico({ ...servico, nomePaz: converterMaiusculas(e.target.value) })}
                        onBlur={e => setServico({ ...servico, nomePaz: removerEspacosExtras(e.target.value) })}
                        required
                        pattern="(?=.*[a-zA-ZáàâãéèêíóôõúçÁÀÂÃÉÈÊÍÓÔÕÚÇ])[a-zA-ZáàâãéèêíóôõúçÁÀÂÃÉÈÊÍÓÔÕÚÇ ]{3,20}"
                        title="O nome deve conter apenas letras e ter entre 3 e 20 caracteres."
                        maxLength="20"
                        minLength="3"
                    />

                    <label>Posto/Grad.:</label>
                    <select name="patente" value={servico.patente} onChange={e => setServico({ ...servico, patente: e.target.value })} required>
                        <option value="" selected disabled>Selecione o Posto ou Graduação</option>
                        <option value="TEN">Tenente</option>
                        <option value="SUBTEN">Subtenente</option>
                        <option value="SGT">Sargento</option>
                        <option value="CB">Cabo</option>
                        <option value="SD">Soldado</option>
                    </select>

                    <label>Antiguidade:</label>
                    <input
                        type="number"
                        placeholder="Ex: 1"
                        value={servico.antiguidade}
                        onChange={e => setServico({ ...servico, antiguidade: e.target.value })}
                        required
                        min="1"
                        max="999"
                        step="1"
                        title="A antiguidade deve ser um número inteiro positivo."
                    />

                    <label>Função:</label>
                    <select name="funcao" value={servico.funcao} onChange={e => setServico({ ...servico, funcao: e.target.value })} required>
                        <option value="" selected disabled>Selecione a Função</option>
                        <option>Fiscal de Dia</option>
                        <option>C.O.V.</option>
                        <option>Operador de Linha</option>
                        <option>Ajudante de Linha</option>
                        <option>Permanente</option>
                    </select>

                    <label>Folga:</label>
                    <input
                        type="number"
                        placeholder="Ex: 3"
                        value={servico.folga}
                        onChange={e => setServico({ ...servico, folga: e.target.value })}
                        required
                        min="1"
                        max="30"
                        step="1"
                        title="A folga deve ser um número inteiro positivo."
                    />

                    <footer>
                        <button type="submit" className={Styles.salvar}>Salvar</button>
                        <button type="button" onClick={fecharModal} className={Styles.cancelar}>Cancelar</button>
                    </footer>

                </form>
            </div>
        </div>
    )
}