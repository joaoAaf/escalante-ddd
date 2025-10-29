import Styles from './styles.module.css'

export default function TabelaMilitares({ militares, setMilitares }) {

    const criarCabeçalho = () => (
        <tr>
            <th>ANTIGUIDADE</th>
            <th>MATRÍCULA</th>
            <th>POST./GRAD.</th>
            <th>NOME DE PAZ</th>
            <th>NASCIMENTO</th>
            <th>FOLGA ESPECIAL</th>
            <th>C.O.V.</th>
        </tr>
    )

    const listarMilitares = () => {
        if (militares === null)
            return (
                <tr>
                    <td colSpan="7">Importe a planilha para carregar os militares.</td>
                </tr>
            )
        return militares.length > 0 ? (
            militares.map((militar) => (
                <tr key={militar.matricula}>
                    {listarDadosMilitar(militar)}
                </tr>
            ))
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