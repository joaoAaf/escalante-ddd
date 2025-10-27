export const criarEscalaAutomatica = async dadosEscala => {
    try {
        const response = await fetch(`http://localhost:8080/escala`, {
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