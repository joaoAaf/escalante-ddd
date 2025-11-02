import { useEffect, useState, useCallback } from 'react'
import BarraPesquisa from '../barra_pesquisa'
import InputUpload from '../input_upload'
import TabelaMilitares from '../tabela_militares'
import FormCriarEscala from '../form_criar_escala'
import Styles from './styles.module.css'
import { obterLocalStorage, salvarLocalStorage } from '../../scripts/persistenciaLocal'

export default function Militares({ setEscala, setTelaAtiva }) {
    const [militares, setMilitares] = useState(null)
    const [militaresFiltrados, setMilitaresFiltrados] = useState(null)
    const [ultimaPesquisa, setUltimaPesquisa] = useState(null)

    const STORAGE_KEY_MILITARES = 'militares'

    obterLocalStorage(STORAGE_KEY_MILITARES, setMilitares)
    salvarLocalStorage(STORAGE_KEY_MILITARES, militares)

    const camposPesquisa = [
        { value: 'nome', label: 'Nome de Paz' },
        { value: 'matricula', label: 'Matrícula' },
        { value: 'cov', label: 'C.O.V.', disableInput: true }
    ]

    const normalize = v => String(v ?? '').toLowerCase()

    const gerenciarPesquisa = useCallback(({ campo, consulta }) => {
        setUltimaPesquisa(pesquisa => {
            if (pesquisa && pesquisa.campo === campo && pesquisa.consulta === consulta) return pesquisa
            return { campo, consulta }
        })
        
        const q = String(consulta ?? '').trim().toLowerCase()

        if (!militares) return

        if (campo === 'cov') {
            const resultadosCov = militares.filter(m => m.cov === true)
            setMilitaresFiltrados(resultadosCov)
            return
        }

        if (!q) {
            setMilitaresFiltrados(militares)
            return
        }

        const resultados = militares.filter(m => {
            switch (campo) {
                case 'matricula':
                    return normalize(m.matricula).includes(q)
                case 'nome':
                    return normalize(m.nomePaz).includes(q)
                default:
                    return false
            }
        })

        setMilitaresFiltrados(resultados)
    }, [militares])

    useEffect(() => {
        if (!ultimaPesquisa) {
            setMilitaresFiltrados(militares)
            return
        }
        gerenciarPesquisa(ultimaPesquisa)
    }, [militares, gerenciarPesquisa, ultimaPesquisa])

    const militaresTabela = militaresFiltrados ?? militares

    return (
        <div className={Styles.main}>
            <h2>Militares Escalaveis</h2>
            <div className={Styles.upload}>
                <label htmlFor="input_upload" className={Styles.label_upload}>Importe a Planilha dos Militares</label>
                <InputUpload setMilitares={setMilitares} />
            </div>
            <FormCriarEscala
                militares={militares}
                setEscala={setEscala}
                setTelaAtiva={setTelaAtiva}
            />
            <BarraPesquisa
                campos={camposPesquisa}
                placeholder="Pesquisar militares..."
                pesquisar={gerenciarPesquisa}
            />
            <TabelaMilitares
                militares={militaresTabela}
                setMilitares={setMilitares}
                sourceMilitares={militares}
            />
        </div>
    )
}