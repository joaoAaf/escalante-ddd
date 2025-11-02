import Delete from './assets/delete.png'
import Styles from './styles.module.css'

export default function BotaoRemover({ tabela, setTabela, id, idKey = 'id' }) {

    const removerLinha = () => {
        if (id === undefined || id === null) return

        const linha = (tabela || []).find(item => String(item?.[idKey]) === String(id))
        if (!linha) return

        const linhaString = Object.entries(linha)
            .map(([chave, valor]) => `${chave}: ${valor}`)
            .join('\n')

        const confirmacao = window.confirm(`Tem certeza que deseja remover o item abaixo?\n\n${linhaString}`)
        if (!confirmacao) return

        const novaTabela = (tabela || []).filter(item => String(item?.[idKey]) !== String(id))
        setTabela(novaTabela)
    }

    return (
        <a
            href="#"
            onClick={e => { e.preventDefault(); removerLinha() }}
            className={Styles.botaoRemover}
        >
            <img src={Delete} alt="Remover" />
        </a>
    )

}