export default class MilitarClient {
    static baseUrl = 'http://localhost:8080/militar'

    static async obterPlanilhaModeloMilitares() {
        try {
            const response = await fetch(`${this.baseUrl}/modelo-planilha`)
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao obter a planilha modelo: ${response.status} ${response.statusText}`)
            return await response.arrayBuffer()
        } catch (error) {
            console.error(error.message)
            throw new Error(`Erro ao obter a planilha modelo: ${error.message}`)
        }
    }

    static async listarMilitaresEscalaveis(arquivo) {
        const formData = new FormData()
        formData.append('militares', arquivo)
        try {
            const response = await fetch(`${this.baseUrl}`, {
                method: 'POST',
                body: formData
            })
            if (!response.status.toString().startsWith('2'))
                throw new Error(`Erro ao listar militares escaláveis: ${response.status} ${response.statusText}`)
            return await response.json()
        } catch (error) {
            console.error(error.message)
            throw new Error(`Erro ao listar militares escaláveis: ${error.message}`)
        }
    }
}