import { useState } from 'react'
import BarraPesquisa from '../barra_pesquisa'
import TabelaEscala from '../tabela_escala'
import Styles from './styles.module.css'

export default function Escala({ escala }) {

    return (
        <div className={Styles.main}>
            <h2>Escala de Serviço</h2>
            <BarraPesquisa />
            <TabelaEscala
                escala={escala}
            />
        </div>
    )
}