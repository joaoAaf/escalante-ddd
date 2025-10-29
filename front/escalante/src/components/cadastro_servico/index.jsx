import Styles from './styles.module.css'

export default function CadastroServico({ statusModal, fecharModal }) {

    if (!statusModal) {
        return null
    }

    return (
        <div className={Styles.modal} onClick={fecharModal}>
            <div onClick={e => e.stopPropagation()}>
                <h2>Adicionar Serviço</h2>
                <form onSubmit="">
                    <label>Data do Serviço:</label>
                    <input type="date" value="" onChange="" required />

                    <label>Matrícula:</label>
                    <input
                        type="text"
                        placeholder="Ex: 1234567X"
                        value=""
                        onChange=""
                        required
                        pattern="[A-Z0-9]{8,8}"
                        title="O código deve conter 8 caracteres, sendo apenas letras maiúsculas e números."
                        maxLength="8"
                        minLength="8"
                    />

                    <label>Militar Escalado:</label>
                    <input
                        type="text"
                        placeholder="Ex: FULANO DE TAL"
                        value=""
                        onChange=""
                        required
                        pattern="[A-Z]{3,20}"
                        title="O nome deve conter apenas letras maiúsculas e ter entre 3 e 20 caracteres."
                        maxLength="20"
                        minLength="3"
                    />

                    <label>Posto/Grad.:</label>
                    <select name="patente" onChange="" required>
                        <option value="" selected disabled>Selecione o Posto ou Graduação</option>
                        <option value="TEN">Tenente</option>
                        <option value="SUBTEN">Subtenente</option>
                        <option value="SGT">Sargento</option>
                        <option value="CB">Cabo</option>
                        <option value="SD">Soldado</option>
                    </select>

                    <label>Antiguidade:</label>
                    <input
                        type="number"
                        placeholder="Ex: 1"
                        value=""
                        onChange=""
                        required
                        min="1"
                        max="999"
                        step="1"
                        title="A antiguidade deve ser um número inteiro positivo."
                    />

                    <label>Função:</label>
                    <select name="funcao" onChange="" required>
                        <option value="" selected disabled>Selecione a Função</option>
                        <option>Fiscal de Dia</option>
                        <option>C.O.V.</option>
                        <option>Operador de Linha</option>
                        <option>Ajudante de Linha</option>
                        <option>Permanente</option>
                    </select>

                    <footer>
                        <button type="submit" className={Styles.salvar}>Salvar</button>
                        <button type="button" onClick={fecharModal} className={Styles.cancelar}>Cancelar</button>
                    </footer>
                </form>
            </div>
        </div>
    )
}