import { useEffect, useState } from 'react'
import Styles from './styles.module.css'

export default function BarraPesquisa({ campos = [{ value: '', label: '' }], placeholder = 'Pesquisar...', pesquisar = () => {} }) {
    const [valorCampo, setValorCampo] = useState(campos[0]?.value ?? '')
    const [consulta, setConsulta] = useState('')

    const campoSelecionado = campos.find(f => f.value === valorCampo) || {}
    const inputType = campoSelecionado.inputType ?? 'text'
    const inputDisabled = campoSelecionado.disableInput ?? false

    useEffect(() => pesquisar({ campo: valorCampo, consulta }), [valorCampo, consulta, pesquisar])

    const limpar = () => setConsulta('')

    const gerenciarMudancaCampos = evento => {
        const valorCampo = evento.target.value
        setValorCampo(valorCampo)
        const novoCampo = campos.find(f => f.value === valorCampo)
        if (novoCampo?.disableInput) setConsulta('')
    }

    return (
        <div className={Styles.search}>
            <select value={valorCampo} onChange={gerenciarMudancaCampos} className={Styles.selectField}>
                {campos.map(f => (
                    <option key={f.value} value={f.value}>{f.label}</option>
                ))}
            </select>
            {Array.isArray(campoSelecionado.options) ? (
                <select value={consulta} onChange={e => setConsulta(e.target.value)} className={Styles.selectField}>
                    <option value="">--</option>
                    {campoSelecionado.options.map(opt => (
                        typeof opt === 'string' ? (
                            <option key={opt} value={opt}>{opt}</option>
                        ) : (
                            <option key={opt.value} value={opt.value}>{opt.label}</option>
                        )
                    ))}
                </select>
            ) : (
                <input
                    type={inputType}
                    placeholder={placeholder}
                    value={consulta}
                    onChange={e => setConsulta(e.target.value)}
                    disabled={inputDisabled}
                />
            )}
            <button className={Styles.clearBtn} onClick={limpar} title="Limpar" disabled={inputDisabled}>✕</button>
        </div>
    )
}