export default class EscalaClient {

    static baseUrl = 'http://localhost:8080/escala'

    static async criarEscalaAutomatica(dadosEscala, signal) {
        try {
            const response = await fetch(`${this.baseUrl}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dadosEscala),
                signal
            })
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao criar escala: ${response.status} ${response.statusText}`)
            return await response.json()
        } catch (error) {
            console.error(error.message)
            if (error.name === 'AbortError') throw error
            throw new Error("Erro ao criar escala: Servidor indisponível.")
        }
    }

    static async exportarEscalaXLSX(escala, signal) {
        try {
            const response = await fetch(`${this.baseUrl}/exportar-xlsx`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(escala),
                signal
            })
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao exportar escala: ${response.status} ${response.statusText}`)
            return await response.arrayBuffer()
        } catch (error) {
            console.error(error.message)
            if (error.name === 'AbortError') throw error
            throw new Error("Erro ao exportar escala: Servidor indisponível.")
        }
    }
}