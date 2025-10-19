import Styles from './styles.module.css'

export default function TabelaMilitares() {
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
                <tr>
                    <td>5</td>
                    <td>123456</td>
                    <td>João Silva</td>
                    <td>01/01/1990</td>
                    <td>CB</td>
                    <td>0</td>
                    <td><input type="checkbox" /></td>
                </tr>
            </tbody>
        </table>
    )
}