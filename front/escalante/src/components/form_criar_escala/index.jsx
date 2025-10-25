import Styles from './styles.module.css'

export default function FormCriarEscala() {
    return (
        <div className={Styles.criar_escala}>
            <h3>Dados para Criação da Escala</h3>
            <form>
                <div>
                    <label htmlFor="data-inicio">Data Inicial:</label>
                    <input type="date" id="data-inicio" />
                </div>

                <div>
                    <label htmlFor="data-fim">Data Final:</label>
                    <input type="date" id="data-fim" />
                </div>

                <div>
                    <label htmlFor="dias-servico">Dias de Serviço:</label>
                    <input type="number" id="dias-servico" />
                </div>
                <div>
                    <button type="submit">Criar Escala</button>
                </div>
            </form>
        </div>
    )
}
