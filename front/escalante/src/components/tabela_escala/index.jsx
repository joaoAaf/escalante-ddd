import { formatarData } from '../../scripts/formatarData'
import Styles from './styles.module.css'

export default function TabelaEscala({ escala }) {

    const criarCabeçalho = () => (
        <tr>
            <th>DATA</th>
            <th>MATRÍCULA</th>
            <th>MILITAR ESCALADO</th>
            <th>POST./GRAD.</th>
            <th>ANTIGUIDADE</th>
            <th>FUNÇÃO</th>
            <th>FOLGA</th>
        </tr>
    )

    const listarEscala = () => {
        if (escala === null)
            return (
                <tr>
                    <td colSpan="7">Vá para a aba <b>Militares</b> para iniciar a criação da escala.</td>
                </tr>
            )
        return escala.length > 0 ? (
            escala.map((servico, index) => (
                <tr key={index}>
                    {listarServicos(servico)}
                </tr>
            ))
        ) : (
            <tr>
                <td colSpan="7">Nenhum serviço encontrado. Tente criar uma nova escala.</td>
            </tr>
        )
    }

    const listarServicos = servico => {
        const servicoDesconstruido = desconstruirServico(servico);
        return Object.keys(servicoDesconstruido).map((campo, index) => (
            <td key={index}>{servicoDesconstruido[campo] ?? "-"}</td>
        ))
    }

    const desconstruirServico = servico => {
        return { ...servico, dataServico: formatarData(servico.dataServico) };
    }

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