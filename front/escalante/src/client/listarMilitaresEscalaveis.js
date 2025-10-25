export const listarMilitaresEscalaveis = async arquivo => {
    const formData = new FormData()
    formData.append('militares', arquivo)
    try {
        const response = await fetch(`http://localhost:8080/militar`, {
            method: 'POST',
            body: formData
        })
        return await response.json()
    } catch (error) {
        alert("Erro ao listar militares escaláveis: " + error.message)
    }
}