import Styles from './styles.module.css'
import BotaoRemover from '../botao_remover'
import { ordenarEscala } from '../../scripts/ordenacaoEscala'

export default function TabelaEscala({ escala, setEscala, sourceEscala }) {

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
            escala.map((servico) => {
                const source = sourceEscala ?? escala
                const id = servico?.id

                const grupo = grupos.get(servico?.dataServico ?? "") ?? 0
                const estiloGrupo = grupo % 2 === 0 ? Styles.grupoPar : Styles.grupoImpar
                const key = id ? `s-${id}` : `s-${servico?.dataServico}-${servico?.matricula}`
                return (
                    <tr key={key} className={estiloGrupo}>
                        {listarServicos(servico, id)}
                        <td>
                            <BotaoRemover
                                id={id}
                                idKey={'id'}
                                tabela={source}
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

    const listarServicos = (servico, id) => {
        return Object.keys(servico).map(campo => {
            if (campo === 'id') return null
            switch (campo) {
                case 'dataServico':
                    return (
                        <td key={campo}>
                            <input
                                type="date"
                                value={servico?.dataServico ?? ""}
                                onChange={e => alterarData(id, e.target.value)}
                            />
                        </td>
                    )
                case 'funcao':
                    return (
                        <td key={campo}>
                            <select
                                value={servico?.funcao ?? ""}
                                onChange={e => alterarFuncao(id, e.target.value)}
                            >
                                <option>Fiscal de Dia</option>
                                <option>C.O.V.</option>
                                <option>Operador de Linha</option>
                                <option>Ajudante de Linha</option>
                                <option>Permanente</option>
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

    const alterarData = (id, novaData) => setEscala(
        escala => {
            if (id == null) return escala
            const novaEscala = escala.map((servico) => servico.id === id ? { ...servico, dataServico: novaData } : servico)
            return ordenarEscala(novaEscala)
        }
    )

    const alterarFuncao = (id, novaFuncao) => setEscala(
        escala => {
            if (id == null) return escala
            const novaEscala = escala.map((servico) => servico.id === id ? { ...servico, funcao: novaFuncao } : servico)
            return ordenarEscala(novaEscala)
        }
    )

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