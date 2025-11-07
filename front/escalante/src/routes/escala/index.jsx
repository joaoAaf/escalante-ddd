import { useState, useEffect, useCallback, useContext } from 'react'
import { GlobalContext } from '../../context/GlobalContext'
import BarraPesquisa from '../../components/barra_pesquisa'
import TabelaEscala from '../../components/tabela_escala'
import Styles from './styles.module.css'
import CadastroServico from '../../components/cadastro_servico'
import AcoesEscala from '../../components/acoes_escala'
import { obterLocalStorage, salvarLocalStorage } from '../../scripts/persistenciaLocal'
import { CadastroServicoContextProvider } from '../../context/CadastroServicoContext'

export default function Escala() {

    const { escala, setEscala } = useContext(GlobalContext)

    const [escalaFiltrada, setEscalaFiltrada] = useState(null)
    const [ultimaPesquisa, setUltimaPesquisa] = useState(null)

    const STORAGE_KEY_ESCALA = 'escala'

    obterLocalStorage(STORAGE_KEY_ESCALA, setEscala)
    salvarLocalStorage(STORAGE_KEY_ESCALA, escala)

    const camposPesquisa = [
        { value: 'data', label: 'Data', inputType: 'date' },
        { value: 'matricula', label: 'Matrícula' },
        { value: 'militar', label: 'Militar Escalado' },
        {
            value: 'funcao',
            label: 'Função',
            options: [
                'Fiscal de Dia',
                'C.O.V.',
                'Operador de Linha',
                'Ajudante de Linha',
                'Permanente'
            ]
        }
    ]

    const normalize = v => String(v ?? '').toLowerCase()

    const gerenciarPesquisa = useCallback(({ campo, consulta }) => {
        setUltimaPesquisa(pesquisa => {
            if (pesquisa && pesquisa.campo === campo && pesquisa.consulta === consulta) return pesquisa
            return { campo, consulta }
        })
        const q = String(consulta ?? '').trim().toLowerCase()

        if (!escala) return

        if (campo === 'data') {
            if (!q) {
                setEscalaFiltrada(escala)
                return
            }
            const resultadosData = escala.filter(servico => {
                return (servico.dataServico === q)
            })
            setEscalaFiltrada(resultadosData)
            return
        }

        if (!q) {
            setEscalaFiltrada(escala)
            return
        }

        const resultados = escala.filter(servico => {
            switch (campo) {
                case 'matricula':
                    return normalize(servico.matricula ?? '').includes(q)
                case 'militar':
                    return normalize(servico.nomePaz ?? '').includes(q)
                case 'funcao':
                    return normalize(servico.funcao ?? '').includes(q)
                default:
                    return false
            }
        })

        setEscalaFiltrada(resultados)
    }, [escala, setEscalaFiltrada])

    useEffect(() => {
        if (!ultimaPesquisa) {
            setEscalaFiltrada(escala)
            return
        }
        gerenciarPesquisa(ultimaPesquisa)
    }, [escala, gerenciarPesquisa, ultimaPesquisa])

    const escalaTabela = escalaFiltrada ?? escala

    return (
        <div className={Styles.main}>
            <h2>Escala de Serviço</h2>
            <CadastroServicoContextProvider>
                <AcoesEscala />
                <CadastroServico />
            </CadastroServicoContextProvider>
            <BarraPesquisa
                campos={camposPesquisa}
                placeholder="Pesquisar na escala..."
                pesquisar={gerenciarPesquisa}
            />
            <TabelaEscala escalaTabela={escalaTabela} />
        </div>
    )
}