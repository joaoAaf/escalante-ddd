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
            return await response.json()
        } catch (error) {
            alert("Erro ao criar escala: " + error.message)
        }
    }
}