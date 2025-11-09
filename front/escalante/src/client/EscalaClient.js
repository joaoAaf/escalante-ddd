export default class EscalaClient {

    static baseUrl = 'http://localhost:8080/escala'

    static async criarEscalaAutomatica(dadosEscala) {
        try {
            const response = await fetch(`${this.baseUrl}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dadosEscala)
            })
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao criar escala: ${response.status} ${response.statusText}`)
            return await response.json()
        } catch (error) {
            console.error(error.message)
            throw new Error(`Erro ao criar escala: ${error.message}`)
        }
    }

    static async exportarEscalaXLSX(escala) {
        try {
            const response = await fetch(`${this.baseUrl}/exportar-xlsx`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(escala)
            })
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao exportar escala: ${response.status} ${response.statusText}`)
            return await response.arrayBuffer()
        } catch (error) {
            console.error(error.message)
            throw new Error(`Erro ao exportar escala: ${error.message}`)
        }
    }
}