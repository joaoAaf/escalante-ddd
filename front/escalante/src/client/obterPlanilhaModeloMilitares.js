export const obterPlanilhaModeloMilitares = async () => {
    try {
        const response = await fetch(`http://localhost:8080/militar/modelo-planilha`, {
            method: 'GET'
        })
        return await response.arrayBuffer()
    } catch (error) {
        alert("Erro ao obter a planilha modelo: " + error.message)
    }
}