export default class MilitarClient {
    static baseUrl = 'http://localhost:8080/militar'

    static async obterPlanilhaModeloMilitares() {
        try {
            const response = await fetch(`${this.baseUrl}/modelo-planilha`)
            return await response.arrayBuffer()
        } catch (error) {
            alert("Erro ao obter a planilha modelo: " + error.message)
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
            return await response.json()
        } catch (error) {
            alert("Erro ao listar militares escaláveis: " + error.message)
        }
    }
}