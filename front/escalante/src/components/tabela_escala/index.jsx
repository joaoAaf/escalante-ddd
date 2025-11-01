import Styles from './styles.module.css'
import BotaoRemover from '../botao_remover'
import { ordenarEscala } from '../../scripts/ordenacaoEscala'

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