import { useEffect } from 'react'

export const salvarLocalStorage = (chave, valor) => {
    useEffect(() => {
        try {
            if (valor === null) localStorage.removeItem(chave)
            else localStorage.setItem(chave, JSON.stringify(valor))
        } catch (error) {
            console.error("Erro ao salvar dados no localStorage:", error)
        }
    }, [valor])
}

export const obterLocalStorage = (chave, setDados) => {
    useEffect(() => {
        try {
            const dadosArmazenados = localStorage.getItem(chave)
            if (dadosArmazenados) setDados(JSON.parse(dadosArmazenados))
        } catch (error) {
            console.error("Erro ao carregar dados do localStorage:", error)
        }
    }, [])
}