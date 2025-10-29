import Styles from './styles.module.css'
import BotaoRemover from '../botao_remover'

export default function TabelaEscala({ escala, setEscala }) {

    const criarCabeçalho = () => (
        <tr>
            <th>DATA</th>
            <th>MATRÍCULA</th>
            <th>MILITAR ESCALADO</th>
            <th>POST./GRAD.</th>
            <th>ANTIGUIDADE</th>
            <th>FUNÇÃO</th>
            <th>FOLGA</th>
            <th>AÇÃO</th>
        </tr>
    )

    const listarEscala = () => {
        if (escala === null)
            return (
                <tr>
                    <td colSpan="7">Vá para a aba <b>Militares</b> para iniciar a criação da escala.</td>
                </tr>
            )

        const grupos = separarGruposPorData(escala)

        return escala.length > 0 ? (
            escala.map((servico, index) => {
                const grupo = grupos.get(servico?.dataServico ?? "") ?? 0
                const estiloGrupo = grupo % 2 === 0 ? Styles.grupoPar : Styles.grupoImpar
                return (
                    <tr key={index} className={estiloGrupo}>
                        {listarServicos(servico, index)}
                        <td>
                            <BotaoRemover
                                index={index}
                                tabela={escala}
                                setTabela={setEscala}
                            />
                        </td>
                    </tr>
                )
            })

        ) : (
            <tr>
                <td colSpan="7">Nenhum serviço encontrado. Tente criar uma nova escala.</td>
            </tr>
        )
    }

    const separarGruposPorData = escala => {
        const mapaGrupo = new Map()
        let grupo = 0
        escala.forEach(servico => {
            const dataServico = servico?.dataServico ?? ""
            if (!mapaGrupo.has(dataServico)) mapaGrupo.set(dataServico, grupo++)
        })
        return mapaGrupo
    }

    const listarServicos = (servico, index) => {
        return Object.keys(servico).map(campo => {
            switch (campo) {
                case 'dataServico':
                    return (
                        <td key={campo}>
                            <input
                                type="date"
                                value={servico?.dataServico ?? ""}
                                onChange={e => alterarData(index, e.target.value)}
                            />
                        </td>
                    )
                case 'funcao':
                    return (
                        <td key={campo}>
                            <select
                                value={servico?.funcao ?? ""}
                                onChange={e => alterarFuncao(index, e.target.value)}
                            >
                                {ordemFuncoes.map(funcao => (
                                    <option key={funcao} value={funcao}>{funcao}</option>
                                ))}
                            </select>
                        </td>
                    )
                default:
                    return (
                        <td key={campo}>{servico[campo] ?? "-"}</td>
                    )
            }
        })
    }

    const alterarData = (index, novaData) => setEscala(
        escala => {
            const novaEscala = escala.map((servico, i) => i === index ? { ...servico, dataServico: novaData } : servico)
            return ordenarEscala(novaEscala)
        }
    )

    const alterarFuncao = (index, novaFuncao) => setEscala(
        escala => {
            const novaEscala = escala.map((servico, i) => i === index ? { ...servico, funcao: novaFuncao } : servico)
            return ordenarEscala(novaEscala)
        }
    )

    const ordenarEscala = escala => {
        const escalaOrdenada = [...escala]
        escalaOrdenada.sort((servicoA, servicoB) => {
            const dataServicoA = servicoA?.dataServico ? new Date(servicoA.dataServico) : null
            const dataServicoB = servicoB?.dataServico ? new Date(servicoB.dataServico) : null

            if ((dataServicoA && dataServicoB) && (dataServicoA.getTime() !== dataServicoB.getTime()))
                return dataServicoA - dataServicoB
            if (dataServicoA && !dataServicoB) return -1
            if (!dataServicoA && dataServicoB) return 1

            const prioridadeFuncaoA = prioridadeFuncao(servicoA?.funcao)
            const prioridadeFuncaoB = prioridadeFuncao(servicoB?.funcao)
            if (prioridadeFuncaoA !== prioridadeFuncaoB)
                return prioridadeFuncaoA - prioridadeFuncaoB

            const antiguidadeServicoA = Number(servicoA?.antiguidade ?? 0)
            const antiguidadeServicoB = Number(servicoB?.antiguidade ?? 0)
            return antiguidadeServicoA - antiguidadeServicoB
        })
        return escalaOrdenada
    }

    const prioridadeFuncao = funcao => {
        const prioridade = ordemFuncoes.indexOf(funcao)
        return prioridade === -1 ? ordemFuncoes.length : prioridade
    }

    const ordemFuncoes = [
        'Fiscal de Dia',
        'C.O.V.',
        'Operador de Linha',
        'Ajudante de Linha',
        'Permanente'
    ]

    return (
        <table className={Styles.table}>
            <thead>
                {criarCabeçalho()}
            </thead>
            <tbody>
                {listarEscala()}
            </tbody>
        </table>
    )
}