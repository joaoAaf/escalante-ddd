import { useContext } from 'react'
import { GlobalContext } from '../../context/GlobalContext'
import Styles from './styles.module.css'
import BotaoRemover from '../botao_remover'

export default function TabelaMilitares({ militaresTabela }) {

    const { militares, setMilitares } = useContext(GlobalContext)

    const criarCabeçalho = () => (
        <tr>
            <th>ANTIGUIDADE</th>
            <th>MATRÍCULA</th>
            <th>POST./GRAD.</th>
            <th>NOME DE PAZ</th>
            <th>NASCIMENTO</th>
            <th>FOLGA ESPECIAL</th>
            <th>C.O.V.</th>
            <th>AÇÃO</th>
        </tr>
    )

    const listarMilitares = () => {
        if (militaresTabela === null)
            return (
                <tr>
                    <td colSpan="7">Importe a planilha para carregar os militares.</td>
                </tr>
            )
        return militaresTabela.length > 0 ? (
                militaresTabela.map((militar, index) => {
                    const source = militares ?? militaresTabela
                    const key = (militar?.matricula ?? '') || `m-${index}`
                    return (
                        <tr key={key}>
                            {listarDadosMilitar(militar)}
                            <td>
                                <BotaoRemover
                                    id={militar?.matricula}
                                    idKey={'matricula'}
                                    tabela={source}
                                    setTabela={setMilitares}
                                />
                            </td>
                        </tr>
                    )
                })
        ) : (
            <tr>
                <td colSpan="7">Nenhum militar encontrado. Importe a planilha.</td>
            </tr>
        )
    }

    const listarDadosMilitar = militar => {
        const militarFormatado = { ...militar, nascimento: formatarData(militar.nascimento) }
        return Object.keys(militarFormatado).map((campo, index) => militarFormatado[campo] !== militar.cov ? (
            <td key={index}>{militarFormatado[campo] ?? "-"}</td>
        ) : (
            <td key={index}>{checkboxCov(militar)}</td>
        ))
    }

    const formatarData = (dataString) => {
        if (!dataString) return "-"
        try {
            const [ano, mes, dia] = dataString.split("-")
            return `${dia}/${mes}/${ano}`
        } catch (error) {
            console.error("Erro ao formatar data:", dataString, error)
            return "-"
        }
    }

    const checkboxCov = militar => (
        <input
            type="checkbox"
            checked={militar.cov === true}
            onChange={() => alterarCov(militar.matricula)}
        />
    )

    const alterarCov = matricula => setMilitares(
        militares => militares.map(militar => {
            if (militar.matricula === matricula)
                return { ...militar, cov: !militar.cov }
            return militar
        })
    )

    return (
        <table className={Styles.table}>
            <thead>
                {criarCabeçalho()}
            </thead>
            <tbody>
                {listarMilitares()}
            </tbody>
        </table>
    )
}