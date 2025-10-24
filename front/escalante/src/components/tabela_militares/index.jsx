import Styles from './styles.module.css'

export default function TabelaMilitares({ militares }) {
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

    const listarMilitares = () => {
        if (militares.length === 1 && militares[0] === true)
            return (
                <tr>
                    <td colSpan="7">Importe a planilha para carregar os militares.</td>
                </tr>
            )
        return militares.length > 0 ? (
            militares.map((militar) => (
                <tr key={militar.matricula}>
                    <td>{militar.antiguidade ?? "-"}</td>
                    <td>{militar.matricula ?? "-"}</td>
                    <td>{militar.nomePaz ?? "-"}</td>
                    <td>{formatarData(militar.nascimento) ?? "-"}</td>
                    <td>{militar.patente ?? "-"}</td>
                    <td>{militar.folgaEspecial ?? "-"}</td>
                    <td>
                        <input
                            type="checkbox"
                            defaultChecked={militar.cov === true}
                        />
                    </td>
                </tr>
            ))
        ) : (
            <tr>
                <td colSpan="7">Nenhum militar encontrado.</td>
            </tr>
        )
    }

    return (
        <table className={Styles.table}>
            <thead>
                <tr>
                    <th>ANTIGUIDADE</th>
                    <th>MATRÍCULA</th>
                    <th>NOME DE PAZ</th>
                    <th>NASCIMENTO</th>
                    <th>POST./GRAD.</th>
                    <th>FOLGA ESPECIAL</th>
                    <th>C.O.V.</th>
                </tr>
            </thead>
            <tbody>
                {listarMilitares()}
            </tbody>
        </table>
    )
}