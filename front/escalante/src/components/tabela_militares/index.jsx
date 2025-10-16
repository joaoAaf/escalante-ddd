import Styles from './styles.module.css'

export default function TabelaMilitares() {
    return (
        <table className={Styles.table}>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nome</th>
                    <th>Patente</th>
                    <th>Sexo</th>
                    <th>Telefone</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="#">123456</a></td>
                    <td>João Silva</td>
                    <td>Cabo</td>
                    <td>Masculino</td>
                    <td>(11) 9765-4321</td>
                </tr>
                <tr>
                    <td><a href="#">233450</a></td>
                    <td>Paulo Almeida</td>
                    <td>Cabo</td>
                    <td>Calco</td>
                    <td>(11) 4564-1140</td>
                </tr>
                <tr>
                    <td><a href="#">438264</a></td>
                    <td>Cláudia Martins</td>
                    <td>Soldado</td>
                    <td>Masculino</td>
                    <td>(11) 9758-5076</td>
                </tr>
                <tr>
                    <td><a href="#">566428</a></td>
                    <td>Vittári Barreto</td>
                    <td>Cabo</td>
                    <td>Feminino</td>
                    <td>(10) 9768-0190</td>
                </tr>
                <tr>
                    <td><a href="#">862289</a></td>
                    <td>Jose Sourzeiro</td>
                    <td>Cabo</td>
                    <td>Soldado</td>
                    <td>(11) 4758-6610</td>
                </tr>
                <tr>
                    <td><a href="#">434569</a></td>
                    <td>Denis Lagoan</td>
                    <td>Soldado</td>
                    <td>Masculino</td>
                    <td>(11) 9755-3025</td>
                </tr>
                <tr>
                    <td><a href="#">456998</a></td>
                    <td>Antônio Silva</td>
                    <td>Soldado</td>
                    <td>Masculino</td>
                    <td>(11) 9875-8010</td>
                </tr>
                <tr>
                    <td><a href="#">256990</a></td>
                    <td>Alexandre Busso</td>
                    <td>Soldado</td>
                    <td>Masculino</td>
                    <td>(11) 4564-1200</td>
                </tr>
                <tr>
                    <td><a href="#">160900</a></td>
                    <td>Maríam Hille</td>
                    <td>Cabo</td>
                    <td>Masculino</td>
                    <td>(11) 9758-2989</td>
                </tr>
            </tbody>
        </table>
    )
}