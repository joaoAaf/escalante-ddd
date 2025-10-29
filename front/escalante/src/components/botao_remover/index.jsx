import Delete from './assets/delete.png'
import Styles from './styles.module.css'

export default function BotaoRemover({ index, tabela, setTabela }) {

    const removerLinha = () => {
        const linha = tabela[index]

        if (!linha) return

        const linhaString = Object.entries(linha)
            .map(([chave, valor]) => `${chave}: ${valor}`)
            .join('\n')

        const confirmacao = window.confirm(`Tem certeza que deseja remover o item abaixo?\n\n${linhaString}`)

        if (!confirmacao) return

        const novaTabela = tabela.filter((_, i) => i !== index)
        setTabela(novaTabela)
    }

    return (
        <a
            href="#"
            onClick={removerLinha}
            className={Styles.botaoRemover}
        >
            <img src={Delete} alt="Remover" />
        </a>
    )

}